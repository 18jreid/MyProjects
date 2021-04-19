from django.http import JsonResponse
from django.shortcuts import render


# Create your views here.
def fibonacci(n):
    if n == 0:
        return 0
    elif n == 1:
        return 1
    else:
        return fibonacci(n - 1) + fibonacci(n - 2)


def index(request):
    return render(request, 'fib/index.html')


def fibAPI(request):
    resp = {}

    if 'n' not in request.GET:
        resp['Error'] = "Usage: n=[non-negative integer]"
    else:
        n = request.GET['n']
        if len(n) == 0 or not n.isdigit():
            resp['Error'] = "Usage: n=[non-negative integer]"
        else:
            n = int(n)
            if n < 0:
                resp['Error'] = "Usage: n=[non-negative integer]"
            else:
                val = fibonacci(n)
                resp['n'] = n
                resp['fibonacci'] = val
    return JsonResponse(resp)
