from flask_wtf import FlaskForm
from flask_wtf.file import FileAllowed, FileField, FileRequired
from wtforms.fields.html5 import DateField
from wtforms import StringField, PasswordField, BooleanField
from wtforms.validators import DataRequired, Email, ValidationError


class UserForm(FlaskForm):
    birthdate = DateField('Fecha de nacimiento', format='%Y-%m-%d')
    picture = FileField('Seleccione la fotografía a cargar: ', validators=[FileAllowed(
        ["sav"], "solamente se permiten archivos entregados por la aplicación")])
    firstname = StringField('Nombres', validators=[DataRequired()])
    surname = StringField('Apellidos', validators=[DataRequired()])
    email = StringField('Email', validators=[Email()])
    password = PasswordField('Contraseña')        

    def validate_email(self, email):
        user = User.query.filter_by(email=email.data).first()
        if user is not None:
            raise ValidationError('La dirección de correo ya se encuntra en uso.')
