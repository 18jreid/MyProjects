from django.contrib import admin
from django.urls import path
from django.urls.conf import include
from mySite import views

urlpatterns = [
    path('', views.login, name='login'),
    path('signUp', views.signUp, name='signUp'),
    path('registerUser', views.registerUser, name='registerUser'),
    path('signInUser', views.signInUser, name='signIn'),
    path('userDashboard', views.userDashboard, name='userDashboard'),
    path('logout', views.logout_user, name='logout')
]
