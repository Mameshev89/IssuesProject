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

    public List<Issue> searchAll() {
        return repository.findAll();
    }

    public void openIssue(int id) {
        if (repository.findById(id) != null) {
            for (Issue issue : repository.findAll()) {
                if (issue.getId() == id) {
                    issue.setStatus(false);
                }
            }
        }
    }

    public void closeIssue(int id) {
        if (repository.findById(id) != null) {
            for (Issue issue : repository.findAll()) {
                if (issue.getId() == id) {
                    issue.setStatus(true);
                }
            }
        }
    }

    public List<Issue> closed() {
        for (Issue item : repository.findAll()) {
            if (!item.isClosed()) {
                issues.add(item);
            }
        }
        return issues;
    }

    public List<Issue> opened() {
        for (Issue item : repository.findAll()) {
            if (item.isClosed()) {
                issues.add(item);
            }
        }
        return issues;
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

    public List<Issue> searchByAssigned(Predicate<Set> predicate) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getAssigned())) {
                tmp.add(item);
            }
        }
        return tmp;
    }

    public List<Issue> searchByLabel(Predicate<Set> predicate) {
        List<Issue> tmp = new ArrayList<>();
        for (Issue item : repository.findAll()) {
            if (predicate.test(item.getLabel())) {
                tmp.add(item);
            }
        }
        return tmp;
    }
}
