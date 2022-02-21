package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private List<Issue> issues = new ArrayList<>();

    public List<Issue> findAll() {
        return issues;
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public void saveAll(List<Issue> issue) {
        this.issues.addAll(issue);
    }

    public void removeById(int id) {
        issues.removeIf(el -> el.getId() == id);
    }

    public void removeAllElements(List<Issue> item) {
        this.issues.removeAll(item);
    }


    public Issue findById(int id) {
        for (Issue item : issues) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
