from django.http import HttpResponse, HttpResponseRedirect
from django.shortcuts import render


# Create your views here.
from django.urls import reverse

from blog.models import Blog, Comments


def aboutPage(request):
    return render(request, 'blog/about.html')


def tipsPage(request):
    return render(request, 'blog/techtips+css.html')


def homePage(request):
    latest_blogs_list = Blog.objects.order_by('posted')
    latest_blogs_list = latest_blogs_list[len(latest_blogs_list) - 3: len(latest_blogs_list)]
    latest_comments_list = Comments.objects.order_by('blog')
    context = {'latest_blogs_list': reversed(latest_blogs_list),
               'latest_comments_list': latest_comments_list}

    return render(request, 'blog/home.html', context)


def archivePage(request):
    latest_blogs_list = Blog.objects.order_by('posted')
    latest_comments_list = Comments.objects.order_by('blog')
    context = {'latest_blogs_list': reversed(latest_blogs_list),
               'latest_comments_list': latest_comments_list}

    return render(request, 'blog/archive.html', context)


def entry(request, blog_id):
    blog = Blog.objects.get(pk=blog_id)
    context = {
        'blog': blog,
    }
    return render(request, 'blog/entry.html', context)


def commentPage(request, blog_id):
    blog = Blog.objects.get(pk=blog_id)
    context = {
        'blog': blog,
    }

    return render(request, 'blog/comment.html', context)


def submitComment(request, blog_id):
    blog = Blog.objects.get(pk=blog_id)
    blog.num_of_comments += 1

    comment = Comments()
    comment.blog = blog
    comment.commenter = request.POST['commenter']
    comment.email = request.POST['email']
    comment.content = request.POST['content']
    comment.posted = request.POST['posted']

    comment.save()
    blog.save()
    return HttpResponseRedirect(reverse('entry', args=(blog.id,)))
