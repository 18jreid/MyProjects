package com.example.webbrowser;

public class urlNode {
    urlNode last = null;
    String url = "";
    urlNode next = null;

    public urlNode(String url) {
        this.url = url;
        this.last = null;
        this.next = null;
    }

    public urlNode(String url, urlNode last) {
        this.url = url;
        this.last = last;
    }

}
