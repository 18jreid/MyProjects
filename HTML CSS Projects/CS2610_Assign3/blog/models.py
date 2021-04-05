from django.db import models


# Create your models here.
class Blog(models.Model):
    title = models.CharField(max_length=128)
    author = models.CharField(max_length=128)
    content = models.TextField()
    posted = models.DateTimeField()
    num_of_comments = models.IntegerField(default=0)


class Comments(models.Model):
    blog = models.ForeignKey(
        'Blog',
        on_delete=models.CASCADE,
    )
    commenter = models.CharField(max_length=256)
    email = models.EmailField()
    content = models.TextField()
    posted = models.DateTimeField()
