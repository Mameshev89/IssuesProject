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
    private final Repository repo = Mockito.mock(Repository.class);

    private final Manager manager = new Manager(repo);
    private final List<Issue> issues = new ArrayList<>();

    private final Issue first = new Issue(1, "Artem", "Item1", Collections.singleton("Teg12"), Collections.singleton("Nikolai"), 12_55, true);
    private final Issue second = new Issue(2, "Gennadi", "Item2", Collections.singleton("Teg2"), Collections.singleton("Fedor"), 11_45, false);
    private final Issue third = new Issue(3, "Artem", "Item3", Collections.singleton("Teg3"), Collections.singleton("Fedor"), 9_55, true);
    private final Issue fourth = new Issue(4, "Aleksandr", "Item4", Collections.singleton("Teg12"), Collections.singleton("Vasili"), 17_00, false);
    private final Issue fifth = new Issue(5, "Maksim", "Item5", Collections.singleton("Teg543"), Collections.singleton("Michail"), 19_00, false);

    @BeforeEach
    void shouldAd() {
        repo.save(first);
        repo.save(second);
        repo.save(third);
        repo.save(fourth);
        repo.saveAll(issues);
    }
    @Test
    void add() {
        doNothing().when(repo).save(any());
        doReturn(List.of(fifth)).when(repo).findAll();
        manager.add(fifth);
        assertEquals(List.of(fifth), manager.searchAll());

        verify(repo).save(any());
    }

    @Test
    void close() {
        doReturn(List.of(first,third,second,fourth, fifth)).when(repo).findAll();
        manager.closeIssue(2);
        assertEquals(List.of(second,fourth,fifth), manager.closed());
    }

    @Test
    void open() {
        doReturn(List.of(first, third)).when(repo).findAll();
        manager.openIssue(1);
        assertEquals(List.of(first, third), manager.opened());
    }

    @Test
    void closed() {
        doReturn(List.of(second, fourth, fifth)).when(repo).findAll();
        assertEquals(List.of(second, fourth, fifth), manager.closed());
    }

    @Test
    void opened() {
        doReturn(List.of(first, third)).when(repo).findAll();
        assertEquals(List.of(first, third), manager.opened());
    }

    @Test
    void searchByAuthor() {
        doReturn(List.of(first, third)).when(repo).findAll();
        assertEquals(List.of(first, third), manager.searchByAuthor("Artem"));
    }

    @Test
    void searchByAssigned() {
        doReturn(List.of(second, third)).when(repo).findAll();
        Set<String> assigned = Collections.singleton("Fedor");
        Predicate<Set> equalAssigned = t -> t.equals(assigned);
        assertEquals(List.of(second,third), manager.searchByAssigned(equalAssigned));
    }

    @Test
    void searchByLabel() {
        doReturn(List.of(first, fourth)).when(repo).findAll();
        Set<String> label = Collections.singleton("Teg12");
        Predicate<Set> equalLabel = t -> t.equals(label);
        assertEquals(List.of(first,fourth), manager.searchByLabel(equalLabel));
    }
}