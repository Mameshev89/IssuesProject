package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class Manager {
    private Repository repository = new Repository();

    private List<Issue> issues = new ArrayList<>();

    public Manager(Repository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public void close(int id) {
        if (repository.findById(id) != null) {
            for (Issue item : repository.findAll()) {
                item.setClosed(false);
            }
        }
    }

    public void open(int id) {
        if (repository.findById(id) != null) {
            for (Issue item : repository.findAll()) {
                item.setClosed(true);
            }
        }
    }

    public List<Issue> closed() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isClosed()) {
                tmp.add(item);
            }
        }
        return tmp;
    }

    public List<Issue> opened() {
        List<Issue> tmp = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.isClosed()) {
                tmp.add(item);
            }
        }
        return tmp;
    }

    public List<Issue> searchByAuthor(String text) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (item.getAuthor().contains(text)) {
                tmp.add(item);
            }
        }
        return tmp;
    }

    public List<Issue> searchByAssigned(Predicate<Set>predicate) {
        List<Issue>tmp=new ArrayList<>();
        for (Issue item:repository.findAll()) {
            if(predicate.test(item.getAssigned())){
                tmp.add(item);
            }
        }
       return tmp;
    }

    public List<Issue> searchByLabel(Predicate<Set> predicate) {
        List<Issue>tmp=new ArrayList<>();
        for (Issue item:repository.findAll()) {
            if(predicate.test(item.getLabel())){
                tmp.add(item);
            }
        }
        return tmp;
    }
}
