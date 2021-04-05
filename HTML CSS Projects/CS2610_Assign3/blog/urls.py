from django.urls import path

from blog import views

urlpatterns = [
    path('', views.aboutPage, name='index'),
    path('about/', views.aboutPage, name='about'),
    path('tips/', views.tipsPage, name='tips'),
    path('home/', views.homePage, name='home'),
    path('archive/', views.archivePage, name='archive'),
    path('<int:blog_id>/', views.entry, name='entry'),
    path('<int:blog_id>/comment/', views.commentPage, name='comment'),
    path('<int:blog_id>/commented/', views.submitComment, name='commented')
]
