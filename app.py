import os.path
from config import Config
from datetime import datetime
from flask import flash, Flask, render_template, request, redirect, url_for
from flask_login import current_user, login_user, login_required, LoginManager, logout_user, UserMixin
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
from flask_uploads import UploadSet, configure_uploads, send_from_directory, IMAGES
from pandas import DataFrame
from sqlalchemy import func
from sqlalchemy.orm import load_only
from view.FormClasses import UserForm
from werkzeug.security import generate_password_hash, check_password_hash
from werkzeug.urls import url_parse

app = Flask(__name__)
app.config.from_object(Config)
database = SQLAlchemy(app)
migrate = Migrate(app, database)
login = LoginManager(app)
login.login_view = 'login'
login.login_message = 'Por favor inicie sesión para acceder a esta página'

photos = UploadSet("fotos", IMAGES)
configure_uploads(app, photos)


class Grupo(database.Model):
    id = database.Column(database.Integer, primary_key=True)
    descripcion = database.Column(
        database.String(250), index=True, unique=True)

    def __repr__(self):
        return '<Grupo {}>'.format(self.descripcion)


class Usuario(UserMixin, database.Model):
    id = database.Column(database.Integer, primary_key=True)
    nombre = database.Column(database.String(250))
    apellido = database.Column(database.String(250))
    fechanacimiento = database.Column(database.DateTime)
    gruposanguineo = database.Column(database.String(2))
    factorrh = database.Column(database.String(2))
    peso = database.Column(database.Integer)
    estatura = database.Column(database.Integer)
    documento = database.Column(database.String(50))
    tipodocumento = database.Column(database.Integer)
    eps = database.Column(database.String(250))
    niveleducativo = database.Column(database.Integer)
    telefono = database.Column(database.String(30))
    direccion = database.Column(database.String(30))
    institucioneducativa = database.Column(database.String(30))
    fechacreacion = database.Column(
        database.DateTime, index=True, default=datetime.utcnow)
    rol = database.Column(database.Integer)
    idgrupo = database.Column(
        database.Integer, database.ForeignKey('grupo.id'))
    email = database.Column(database.String(250), index=True, unique=True)
    clave = database.Column(database.String(250), unique=True)

    def __repr__(self):
        return '<Usuario {} {}>'.format(self.nombre, self.apellido)

    def set_password(self, password):
        self.clave = generate_password_hash(password)

    def check_password(self, password):
        return check_password_hash(self.clave, password)


class RegistroAsistencia(database.Model):
    id = database.Column(database.Integer, primary_key=True)
    idgrupo = database.Column(
        database.Integer, database.ForeignKey('grupo.id'))
    idusuario = database.Column(
        database.Integer, database.ForeignKey('usuario.id'))
    fecha = database.Column(
        database.Date, index=True, default=datetime.utcnow)

    def __repr__(self):
        return '<Asistencia {} : {}>'.format(self.usuario, self.fecha)


def clearFiles(filename, path):
    filepath = os.path.join(path, filename)
    if os.path.exists(filepath):
        os.remove(filepath)


def getFileExt(filename):
    index = filename.rfind('.')
    if (index != -1):
        return filename[index:]
    return None


def changeName(currentFileName, id):
    ext = getFileExt(currentFileName)
    if ext != None:
        return str(id) + ext
    return None


def findDocTypes(id):
    doc_types = {
        1: 'Cédula de ciudadanía',
        2: 'Tarjeta de identidad',
        3: 'Registro Civil'
    }
    return doc_types.get(id, "Tipo de identificación inválido")


def findEducationLevels(id):
    education_levels = {
        1: 'Primero',
        2: 'Segundo',
        3: 'Tercero',
        4: 'Cuarto',
        5: 'Quinto',
        6: 'Sexto',
        7: 'Septimo',
        8: 'Octavo',
        9: 'Noveno',
        10: 'Décimo',
        11: 'Once',
        12: 'Técnico',
        13: 'Universitario'
    }
    return education_levels.get(id, "No reporta nivel educativo")


def findRoles(id):
    role_types = {
        1: 'Administrador',
        2: 'Colaborador',
        3: 'Estudiante'
    }
    return role_types.get(id, "Rol inválido")


def findGroups(id):
    group = Grupo.query.filter(Grupo.id == int(id)).first()
    return group.descripcion


@login.user_loader
def load_user(id):
    return Usuario.query.get(int(id))


@app.route("/")
def index():
    return render_template("index.html")


@app.route('/login', methods=['GET', 'POST'])
def login():
    myform = request.form
    if current_user.is_authenticated:
        flash("El usuario ya se encuentra autenticado")
        return redirect(url_for('test'))
    if request.method == 'POST':
        user = Usuario.query.filter(func.lower(Usuario.email) == func.lower(
            request.form['inputEmail'])).first()
        if user is None or not user.check_password(request.form['inputPassword']):
            flash('Usuario o contraseña incorrectos')
            return redirect(url_for('login'))
        if request.form.get('remember_me', None) is None:
            login_user(user)
        else:
            login_user(user, remember=True)
        next_page = request.args.get('next')
        if not next_page or url_parse(next_page).netloc != '':
            next_page = url_for('index')
        return redirect(next_page)
    return render_template('login.html', form=myform)


@app.route('/logout')
def logout():
    if current_user.is_authenticated:
        logout_user()
        flash("Sesión finalizada correctamente")
    return redirect(url_for('index'))


@app.route("/usuarios")
@login_required
def user_management():
    page = request.args.get('page', default=1, type=int)
    users = Usuario.query.order_by(Usuario.apellido).paginate(
        page, app.config['ROWS_PER_PAGE'], False)
    if users.has_next:
        next_url = url_for('user_management', page=users.next_num)
    else:
        next_url = None
    if users.has_prev:
        prev_url = url_for('user_management', page=users.prev_num)
    else:
        prev_url = None
    return render_template("users.html", users=users.items, next_url=next_url, prev_url=prev_url)


@app.route("/create_user", methods=['GET', 'POST'])
@login_required
def create_user():
    form = UserForm()
    form.group_id.choices = [(g.id, g.descripcion)
                             for g in Grupo.query.order_by('descripcion')]
    if form.validate_on_submit():
        current_user = Usuario()
        if form.birthdate.data is not None:
            current_user.fechanacimiento = form.birthdate.data
        if form.firstname.data is not None:
            current_user.nombre = form.firstname.data
        if form.surname.data is not None:
            current_user.apellido = form.surname.data
        if form.email.data is not None:
            current_user.email = form.email.data
        if form.password.data is not None:
            current_user.set_password(form.password.data)
        if form.blood_group.data is not None:
            current_user.gruposanguineo = form.blood_group.data
        if form.rh_factor.data is not None:
            current_user.factorrh = form.rh_factor.data
        if form.document_type.data is not None:
            current_user.tipodocumento = form.document_type.data
        if form.document_number.data is not None:
            current_user.documento = form.document_number.data
        if form.role_type.data is not None:
            current_user.rol = form.role_type.data
        if form.eps.data is not None:
            current_user.eps = form.eps.data
        if form.education_level.data is not None:
            current_user.niveleducativo = form.education_level.data
        if form.group_id.data is not None:
            current_user.idgrupo = form.group_id.data
        if form.height.data is not None:
            current_user.estatura = form.height.data
        if form.weight.data is not None:
            current_user.peso = form.weight.data
        if form.educative_plantel.data is not None:
            current_user.institucioneducativa = form.educative_plantel.data
        if form.address.data is not None:
            current_user.direccion = form.address.data
        if form.phone.data is not None:
            current_user.telefono = form.phone.data
        print(current_user)
        database.session.add(current_user)
        database.session.commit()
        return redirect(url_for('user_management'))
    return render_template("user.html", form=form)


@app.route("/get_user/<profile_id>")
@login_required
def query_user(profile_id):
    user = Usuario.query.filter(Usuario.documento == str(profile_id)).first()
    doctype = findDocTypes(user.tipodocumento)
    role = findRoles(user.rol)
    educative_level = findEducationLevels(user.niveleducativo)
    group = findGroups(user.idgrupo)
    return render_template("profile.html", user=user, tipo_documento=doctype, user_role=role, educative_level=educative_level, user_group=group)


@app.route("/export_users")
@login_required
def get_report():
    users = Usuario.query.with_entities(Usuario.nombre, Usuario.apellido, Usuario.fechanacimiento, Usuario.gruposanguineo, Usuario.factorrh, Usuario.peso, Usuario.estatura, Usuario.documento,
                                        Usuario.tipodocumento, Usuario.eps, Usuario.niveleducativo, Usuario.telefono, Usuario.direccion, Usuario.institucioneducativa, Usuario.fechacreacion, Usuario.rol, Usuario.idgrupo, Usuario.email).all()
    dataFrame = DataFrame(users)
    dataFrame['tipodocumento'] = dataFrame['tipodocumento'].apply(findDocTypes)
    dataFrame['rol'] = dataFrame['rol'].apply(findRoles)
    dataFrame['niveleducativo'] = dataFrame['niveleducativo'].apply(
        findEducationLevels)
    dataFrame['idgrupo'] = dataFrame['idgrupo'].apply(findGroups)
    os.makedirs("uploads/results/", exist_ok=True)
    dataFrame.to_html('uploads/results/listado_usuarios.xls')
    print(dataFrame.head())
    dataFrame.to_csv(encoding='utf-8', header=True, index=True,
                     sep=';', path_or_buf=('uploads/results/listado_usuarios.csv'))
    return redirect('uploads/results/listado_usuarios.xls')


@app.route("/delete_user/<profile_id>", methods=['GET', 'POST'])
@login_required
def delete_user(profile_id):
    user = Usuario.query.filter(Usuario.documento == str(profile_id)).first()
    userfullname = user.nombre + ' ' + user.apellido
    database.session.delete(user)
    database.session.commit()
    flash("Se eliminó de la base de datos al usuario: " + userfullname)
    return redirect(url_for('user_management'))


@app.route("/modify_user/<profile_id>", methods=['GET', 'POST'])
@login_required
def modify_user(profile_id):
    form = UserForm()
    form.group_id.choices = [(g.id, g.descripcion)
                             for g in Grupo.query.order_by('descripcion')]
    current_user = Usuario.query.filter(
        Usuario.documento == str(profile_id)).first()
    if request.method == "GET":
        form.firstname.data = current_user.nombre
        form.birthdate.process_data(current_user.fechanacimiento)
        form.surname.data = current_user.apellido
        form.rh_factor.data = current_user.factorrh
        form.document_number.data = current_user.documento
        form.eps.data = current_user.eps
        form.group_id.data = current_user.idgrupo
        form.height.data = current_user.estatura
        form.weight.data = current_user.peso
        form.educative_plantel.data = current_user.institucioneducativa
        form.address.data = current_user.direccion
        form.phone.data = current_user.telefono
        if (current_user.email is not None or current_user.email != ""):
            form.email.data = current_user.email
    if form.validate_on_submit():
        if form.birthdate.data is not None:
            print(form.birthdate.data)
            current_user.fechanacimiento = form.birthdate.data
        if form.firstname.data is not None:
            current_user.nombre = form.firstname.data
        if form.surname.data is not None:
            current_user.apellido = form.surname.data
        if form.email.data is not None:
            current_user.email = form.email.data
        if form.password.data is not None:
            current_user.set_password(form.password.data)
        if form.blood_group.data is not None:
            current_user.gruposanguineo = form.blood_group.data
        if form.rh_factor.data is not None:
            current_user.factorrh = form.rh_factor.data
        if form.document_type.data is not None:
            current_user.tipodocumento = form.document_type.data
        if form.document_number.data is not None:
            current_user.documento = form.document_number.data
        if form.role_type.data is not None:
            current_user.rol = form.role_type.data
        if form.eps.data is not None:
            current_user.eps = form.eps.data
        if form.education_level.data is not None:
            current_user.niveleducativo = form.education_level.data
        if form.group_id.data is not None:
            current_user.idgrupo = form.group_id.data
        if form.height.data is not None:
            current_user.estatura = form.height.data
        if form.weight.data is not None:
            current_user.peso = form.weight.data
        if form.educative_plantel.data is not None:
            current_user.institucioneducativa = form.educative_plantel.data
        if form.address.data is not None:
            current_user.direccion = form.address.data
        if form.phone.data is not None:
            current_user.telefono = form.phone.data
        database.session.commit()
        flash("el usuario: " + current_user.nombre + ' ' +
              current_user.apellido + " fue actualizado correctamente")
        return redirect(url_for('user_management'))
    return render_template("user.html", form=form)


@app.route("/test")
def test():
    form = UserForm()
    return render_template("index.html", form=form)


@app.route('/uploads/<path:filename>', methods=['GET', 'POST'])
def download(filename):
    uploads = os.path.join(app.root_path, app.config['UPLOADS_DEFAULT_DEST'])
    return send_from_directory(directory=uploads, filename=filename)


@app.errorhandler(404)
def page_not_found(e):
    return render_template('404.html', error_type=404), 404


@app.errorhandler(500)
def error(e):
    return render_template('404.html', error_type=500), 500


if __name__ == "__main__":
    app.run()
