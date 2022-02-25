package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.domain.Issue;
import ru.netology.repository.Repository;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ManagerTest {
    private final Repository repo = new Repository();
    private final Manager manager = new Manager(repo);
    private final List<Issue> issues = new ArrayList<>();

    private final Issue first = new Issue(1, "Artem", "Item1", Collections.singleton("Teg12"), Collections.singleton("Nickolai"), 12_55, true);
    private final Issue second = new Issue(2, "Gennadi", "Item2", Collections.singleton("Teg2"), Collections.singleton("Fedor"), 11_45, false);
    private final Issue third = new Issue(3, "Artem", "Item3", Collections.singleton("Teg3"), Collections.singleton("Fedor"), 9_55, true);
    private final Issue fourth = new Issue(4, "Aleksandr", "Item4", Collections.singleton("Teg12"), Collections.singleton("Vasily"), 17_00, false);
    private final Issue fifth = new Issue(5, "Maksim", "Item5", Collections.singleton("Teg543"), Collections.singleton("Michail"), 19_00, false);

    @BeforeEach
    void shouldAd() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
    }

    @Test
    void shouldFindAll() {
        assertEquals(List.of(first, second, third, fourth, fifth), manager.searchAll());
    }


    @Test
    void close() {
        manager.closeIssue(5);
        assertEquals(List.of(first, third, fifth), manager.isClosed());
    }


    @Test
    void open() {
        manager.openIssue(3);
        assertEquals(List.of(second, third, fourth, fifth), manager.isOpened());
    }

    @Test
    void searchByAuthor() {
        assertEquals(List.of(first, third), manager.searchByAuthor("Artem"));
    }

    @Test
    void searchByAssigned() {
        Set<String> assigned = Collections.singleton("Fedor");
        Predicate<Set> equalAssigned = t -> t.equals(assigned);
        assertEquals(List.of(second, third), manager.searchByAssigned(equalAssigned));
    }

    @Test
    void searchByLabel() {
        Set<String> label = Collections.singleton("Teg12");
        Predicate<Set> equalLabel = t -> t.equals(label);
        assertEquals(List.of(first, fourth), manager.searchByLabel(equalLabel));
    }
}