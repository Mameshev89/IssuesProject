package ru.netology.domain;

import java.util.Set;

public class Issue {
    private int id;
    private String author;
    private String title;
    private Set<String>label;
    private Set<String> assigned;
    private int date;
    private boolean closed;

    public Issue(int id, String author, String title, Set<String> label, Set<String> assigned, int date, boolean closed) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.label = label;
        this.assigned = assigned;
        this.date = date;
        this.closed = closed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<String> getLabel() {
        return label;
    }

    public void setLabel(Set<String> label) {
        this.label = label;
    }

    public Set<String> getAssigned() {
        return assigned;
    }

    public void setAssigned(Set<String> assigned) {
        this.assigned = assigned;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setStatus(boolean closed) {
        this.closed = closed;
    }
}
