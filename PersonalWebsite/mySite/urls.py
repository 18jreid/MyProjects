from django.urls import path

from mySite import views

urlpatterns = [
    path('', views.portfolioPage, name='portfolio'),
    path('projects/', views.projectsPage, name='projects'),
    path('contact/', views.contactPage, name='contact'),
    path('color/', views.colorPage, name='color'),
]
