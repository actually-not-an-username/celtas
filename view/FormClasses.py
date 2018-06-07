from flask_wtf import FlaskForm
from flask_wtf.file import FileAllowed, FileField, FileRequired
from wtforms import StringField, PasswordField, BooleanField, SelectField, IntegerField
from wtforms.fields.html5 import DateField
from wtforms.validators import DataRequired, Email, ValidationError, Optional


class UserForm(FlaskForm):
    birthdate = DateField('Fecha de nacimiento', format='%Y-%m-%d')
    '''picture = FileField('Seleccione la foto: ', validators=[FileRequired(
        message="Archivo requerido"), FileAllowed(
        ['.jpg', '.jpe', '.jpeg', '.png', '.gif', '.svg', '.bmp'], "solamente se permiten fotos")])'''
    firstname = StringField('Nombres', validators=[DataRequired()])
    surname = StringField('Apellidos', validators=[DataRequired()])
    password = PasswordField('Contraseña')
    blood_group = SelectField(
        'Grupo Sanguineo', choices=[('A', 'A'), ('B', 'B'), ('AB', 'AB'), ('O', 'O')], validators=[DataRequired()]
    )
    rh_factor = SelectField(
        'Factor RH', choices=[('+', '+'), ('-', '-')], validators=[DataRequired()]
    )
    document_type = SelectField(
        'Tipo de documento', choices=[('1', 'Cédula de ciudadanía'), ('2', 'Tarjeta de identidad'), ('3', 'Registro Civil')], validators=[DataRequired()]
    )
    document_number = StringField(
        'Número de documento', validators=[DataRequired()])
    role_type = SelectField(
        'Rol', choices=[('1', 'Administrador'), ('2', 'Colaborador'), ('3', 'Estudiante')], validators=[DataRequired()]
    )
    eps = StringField('E.P.S', validators=[DataRequired()])
    education_level = SelectField(
        'Grado de escolaridad', choices=[('1', 'Primero'), ('2', 'Segundo'), ('3', 'Tercero'), ('4', 'Cuarto'), ('5', 'Quinto'), ('6', 'Sexto'), ('7', 'Séptimo'), ('8', 'Octavo'), ('9', 'Noveno'), ('10', 'Décimo'), ('11', 'Once'), ('12', 'Técnico'), ('13', 'Univesitario')], validators=[DataRequired()]
    )
    group_id = SelectField(u'Grupo', coerce=int)
    height = IntegerField('Estatura en centimetros',
                          validators=[DataRequired()])
    weight = IntegerField('Peso en kilogramos', validators=[DataRequired()])
    educative_plantel = StringField('Institución educativa')
    address = StringField('Dirección', validators=[DataRequired()])
    phone = StringField('Telefono', validators=[DataRequired()])
    email = StringField('Email', validators=[Optional(), Email()])
