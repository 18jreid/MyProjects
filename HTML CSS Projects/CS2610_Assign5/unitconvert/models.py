from django.db import models


# Create your models here.
class lb(models.Model):
    lb = models.fields.FloatField(default=1)
    oz = models.fields.FloatField(default=16)
    t_oz = models.fields.FloatField(default=14.5833)
    kg = models.fields.FloatField(default=0.453592)
    g = models.fields.FloatField(default=453.592)


class Ounces(models.Model):
    lb = models.fields.FloatField(default=0.0625)
    oz = models.fields.FloatField(default=1)
    t_oz = models.fields.FloatField(default=0.911458)
    kg = models.fields.FloatField(default=0.0283495)
    g = models.fields.FloatField(default=28.3495)


class t_oz(models.Model):
    lb = models.fields.FloatField(default=0.0685714)
    oz = models.fields.FloatField(default=1.09714)
    t_oz = models.fields.FloatField(default=1)
    kg = models.fields.FloatField(default=0.0311035)
    g = models.fields.FloatField(default=31.1035)


class kg(models.Model):
    lb = models.fields.FloatField(default=2.20462)
    oz = models.fields.FloatField(default=35.274)
    t_oz = models.fields.FloatField(default=32.1507)
    kg = models.fields.FloatField(default=1)
    g = models.fields.FloatField(default=1000)


class g(models.Model):
    lb = models.fields.FloatField(default=0.00220462)
    oz = models.fields.FloatField(default=0.035274)
    t_oz = models.fields.FloatField(default=0.0321507)
    kg = models.fields.FloatField(default=0.001)
    g = models.fields.FloatField(default=1)
