from django import contrib
import django
from django.contrib import auth
from django.contrib.auth.signals import user_logged_out
from django.http.response import HttpResponse
from django.shortcuts import redirect, render
from django.contrib.auth.models import User
from django.contrib.auth import authenticate, logout, password_validation

# Create your views here.


def login(request):
    if (request.user.is_authenticated):
        return redirect('userDashboard')
    else:
        return render(request, "mySite/login.html")

def logout_user(request):
    auth.logout(request)

    return redirect('login')

def signUp(request):
    return render(request, "mySite/signUp.html")

def userDashboard(request):
    if (request.user.is_authenticated):
        context = {
            'user' : request.user.first_name
        }
        return render(request, "mySite/userDashboard.html", context)
    else:
        return redirect('login')

def registerUser(request):
    userName = request.POST['userID']
    firstName = request.POST['firstName']
    lastName = request.POST['lastName']
    email = request.POST['userEmail']
    emailConfirmation = request.POST['userEmailConfirmation']
    password = request.POST['password']
    passwordConfirmation = request.POST['passwordConfirmation']

    return checkSignUpFields(request, userName, firstName, lastName, email, emailConfirmation, password, passwordConfirmation)

def checkSignUpFields(request, userName, firstName, lastName, email, emailConfirmation, password, passwordConfirmation):
    context = {}
    if (firstName == ""):
        print("\n \n FIRST NAME IS EMPTY \n \n ")
        context = {
            'error' : "First name is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (lastName == ""):
        print("\n \n LAST NAME IS EMPTY \n \n")
        context = {
            'error' : "Last name is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (userName == ""):
        print("\n \n USERNAME IS EMPTY \n \n")
        context = {
            'error' : "Username is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (email == ""):
        print("\n \n EMAIL IS EMPTY \n \n ")
        context = {
            'error' : "Email is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (emailConfirmation == ""):
        print("\n \n EMAIL CONFIRMATION IS EMPTY \n \n ")
        context = {
            'error' : "Email confirmation is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (password == ""):
        print("\n \n  PASSWORD IS EMPTY \n \n ")
        context = {
            'error' : "Password is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (passwordConfirmation == ""):
        print("\n \n PASSWORD CONFIRMATION IS EMPTY\n \n ")
        context = {
            'error' : "Password Confirmation is empty"
        }
        return render(request, "mySite/signUp.html", context)

    if (email != emailConfirmation):
        print("\n \n EMAILS DO NOT MATCH")
        context = {
            'error' : "Emails don't match"
        }
        return render(request, "mySite/signUp.html", context)
    
    if (password != passwordConfirmation):
        print("\n \n PASSWORDS DO NOT MATCH \n \n")
        context = {
            'error' : "Passwords do not match"
        }
        return render(request, "mySite/signUp.html", context)

    userObjects =  User.objects.all()

    for user in userObjects:
        if (user.username == userName):
            context = {
                'error': "User already exists"
            }
            print("\n \n USER ALREADY EXISTS \n \n")
            return render(request, "mySite/signUp.html", context)

        if (user.email == email):
            context = {
                'error': "User already exists"
            }
            print("\n \n USER ALREADY EXISTS \n \n")
            return render(request, "mySite/signUp.html", context)

    user = User.objects.create_user(userName, email, password)
    user.first_name = firstName
    user.last_name = lastName
    user.save()
    
    return render(request, "mySite/login.html")

def signInUser(request):
    userName = request.POST['userID']
    password = request.POST['password']

    myUser = authenticate(username=userName, password=password)

    if (myUser is not None):
        auth.login(request=request, user=myUser)
        return redirect('userDashboard')
    else:
        context = {
            'error' : "User does not exist"
        }
        return render(request, "mySite/login.html", context)

def checkIfUserExists(userName, email):
    userObjects =  User.objects.all()

    for user in userObjects:
        if (user.username == userName):
            return False
        if (user.email == email):
            return False
    
    return True