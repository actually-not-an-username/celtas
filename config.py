import os
from datetime import timedelta

class Config(object):
    SECRET_KEY = os.environ.get('SECRET_KEY') or 'you-will-never-guess'
    UPLOADS_DEFAULT_DEST = "uploads"
    DEBUG = False
    SQLALCHEMY_DATABASE_URI = 'postgresql://postgres:dracula2000@localhost:5432/postgres'
    SQLALCHEMY_TRACK_MODIFICATIONS = False
    REMEMBER_COOKIE_DURATION = os.environ.get('REMEMBER_COOKIE_DURATION') or timedelta(seconds = 60)
    ROWS_PER_PAGE = 20