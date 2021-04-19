from sqlite3.dbapi2 import Date

from django.shortcuts import render


# Create your views here.
def index(request):
    date = Date

    context = {
        'date': date.today()
    }
    return render(request, 'gold/index.html', context)
