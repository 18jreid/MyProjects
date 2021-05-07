from django.http import HttpResponse
from django.shortcuts import render


# Create your views here.
def portfolioPage(request):
    return render(request, 'mySite/index.html')


def projectsPage(request):
    return render(request, 'mySite/projects.html')


def contactPage(request):
    return render(request, 'mySite/contact.html')

def colorPage(request):
    return render(request, 'mySite/color.html')
