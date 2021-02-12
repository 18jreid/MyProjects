package com.example.webbrowser;

public class LinkedList {
    String url;
    urlNode head;
    urlNode curr;

    public LinkedList(String urlHead) {
        this.url = urlHead;
        this.head = new urlNode(urlHead);
        this.curr = head;
    }

    void addUrl(String url, urlNode node) {
        if (node.next != null) {
            addUrl(url, node.next);
        }
        else {
            node.next = new urlNode(url, node);
            this.curr = node.next;
        }
    }

    void changeCurrToLast() {
        this.curr = curr.last;
    }

    void changeCurrToNext() {
        this.curr = curr.next;
    }

    void changeCurrToNewest(urlNode node) {
        if (node.next != null) {
            changeCurrToNewest(node.next);
        }
        else {
            this.curr = node;
        }
    }
}
