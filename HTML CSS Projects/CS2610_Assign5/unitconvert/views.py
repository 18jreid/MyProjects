from django.http import JsonResponse, HttpResponse
from django.shortcuts import render
from unitconvert.models import lb, Ounces, t_oz, kg, g
import unitconvert.models as models


def index(request):
    return render(request, 'unitconvert/index.html')


# Create your views here.
def convert(request):
    resp = {}

    unitFrom = request.GET['from']
    if not checkFromAndToGetRequests(unitFrom):
        resp['Error'] = 'Usage: from=[lb, oz, t_oz, kg, or g]'
        return JsonResponse(resp)

    unitTo = request.GET['to']
    if not checkFromAndToGetRequests(unitTo):
        resp['Error'] = 'Usage: from=[lb, oz, t_oz, kg, or g]'
        return JsonResponse(resp)

    value = request.GET['value']
    if not checkValueGetRequest(value):
        resp['Error'] = 'Usage: value=[non-negative integer]'
        return JsonResponse(resp)

    resp['units'] = unitTo
    resp['value'] = convertTo(unitFrom, unitTo, eval(value))

    return JsonResponse(resp)


def checkFromAndToGetRequests(get):
    if get == 'lb' or get == 'oz' or get == 't_oz' or get == 'kg' or get == 'g':
        return True
    else:
        return False


def checkValueGetRequest(get):
    if get and eval(get) >= 0:
        return True
    else:
        return False


def convertTo(unitFrom, unitTo, value):
    if unitFrom == 'lb':
        value = convertPounds(value, unitTo)
    elif unitFrom == 'oz':
        value = convertOz(value, unitTo)
    elif unitFrom == 't_oz':
        value = convertT_oz(value, unitTo)
    elif unitFrom == 'kg':
        value = convertKg(value, unitTo)
    elif unitFrom == 'g':
        value = convertG(value, unitTo)

    return value


def convertPounds(value, to):
    if to == 'oz':
        value *= lb.objects.get(pk=1).oz
    elif to == 'lb':
        value *= lb.objects.get(pk=1).lb
    elif to == 't_oz':
        value *= lb.objects.get(pk=1).t_oz
    elif to == 'kg':
        value *= lb.objects.get(pk=1).kg
    elif to == 'g':
        value *= lb.objects.get(pk=1).g

    return value


def convertOz(value, to):
    if to == 'oz':
        value *= Ounces.objects.get(pk=1).oz
    elif to == 'lb':
        value *= Ounces.objects.get(pk=1).lb
    elif to == 't_oz':
        value *= Ounces.objects.get(pk=1).t_oz
    elif to == 'kg':
        value *= Ounces.objects.get(pk=1).kg
    elif to == 'g':
        value *= Ounces.objects.get(pk=1).g

    return value


def convertT_oz(value, to):
    if to == 'oz':
        value *= t_oz.objects.get(pk=1).oz
    elif to == 'lb':
        value *= t_oz.objects.get(pk=1).lb
    elif to == 't_oz':
        value *= t_oz.objects.get(pk=1).t_oz
    elif to == 'kg':
        value *= t_oz.objects.get(pk=1).kg
    elif to == 'g':
        value *= t_oz.objects.get(pk=1).g

    return value


def convertKg(value, to):
    if to == 'oz':
        value *= kg.objects.get(pk=1).oz
    elif to == 'lb':
        value *= kg.objects.get(pk=1).lb
    elif to == 't_oz':
        value *= kg.objects.get(pk=1).t_oz
    elif to == 'kg':
        value *= kg.objects.get(pk=1).kg
    elif to == 'g':
        value *= kg.objects.get(pk=1).g

    return value


def convertG(value, to):
    if to == 'oz':
        value *= g.objects.get(pk=1).oz
    elif to == 'lb':
        value *= g.objects.get(pk=1).lb
    elif to == 't_oz':
        value *= g.objects.get(pk=1).t_oz
    elif to == 'kg':
        value *= g.objects.get(pk=1).kg
    elif to == 'g':
        value *= g.objects.get(pk=1).g

    return value
