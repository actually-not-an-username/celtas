import os.path
from config import Config
from datetime import datetime
from flask import flash, Flask, render_template, request, redirect, url_for
from flask_login import current_user, login_user, login_required, LoginManager, logout_user, UserMixin
from flask_migrate import Migrate
from flask_sqlalchemy import SQLAlchemy
from flask_uploads import UploadSet, configure_uploads, send_from_directory
from sqlalchemy import func
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

datasets = UploadSet("datasets", ("txt", "csv", "xls", "xlsx"))
configure_uploads(app, datasets)


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
    localidad = database.Column(database.Integer)
    telefono = database.Column(database.String(30))
    direccion = database.Column(database.String(30))
    institucioneducativa = database.Column(database.String(30))
    fechacreacion = database.Column(
        database.DateTime, index=True, default=datetime.utcnow)
    activo = database.Column(database.Boolean)
    estadoregistro = database.Column(database.Integer)
    rol = database.Column(database.Integer)
    idgrupo = database.Column(
        database.Integer, database.ForeignKey('grupo.id'))
    email = database.Column(database.String(250), index=True, unique=True)
    clave = database.Column(database.String(250), unique=True)
    acudientes = database.relationship(
        'Relativos', backref='acudientes', lazy='dynamic')

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


class Relativos(database.Model):
    id = database.Column(database.Integer, primary_key=True)
    parentesco = database.Column(database.String(250))
    nombre = database.Column(database.String(250))
    apellido = database.Column(database.String(250))
    telefono = database.Column(database.String(30))
    direccion = database.Column(database.String(30))
    ocupacion = database.Column(database.String(250))
    idusuario = database.Column(
        database.Integer, database.ForeignKey('usuario.id'))

    def __repr__(self):
        return '<Acudiente {} {}>'.format(self.nombre, self.apellido)


@login.user_loader
def load_user(id):
    return Usuario.query.get(int(id))


@app.route("/")
def index():
    return render_template("layout.html")


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
    return render_template("layout.html")


@app.route("/get_user/<profile_id>")
@login_required
def query_user():    
    return render_template("users.html", users=users.items, next_url=next_url, prev_url=prev_url)


@app.route("/update_user/<profile_id>", methods=['GET', 'POST'])
@login_required
def update_user():
    return render_template("users.html", users=users.items, next_url=next_url, prev_url=prev_url)


@app.route("/asistencia")
@login_required
def asistencia():
    return render_template("layout.html")


@app.route("/test")
def test():
    form = UserForm()
    return render_template("texto.html", form = form)


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
