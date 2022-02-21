package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RepositoryTest {

    private final Repository repo = new Repository();
    private final List<Issue> issues = new ArrayList<>();

    private final Issue first = new Issue(1, "Artem", "Item1", Collections.singleton("Teg12"), Collections.singleton("Николай"), 12_55, true);
    private final Issue second = new Issue(2, "Gennadi", "Item2", Collections.singleton("Teg2"), Collections.singleton("Федор"), 11_45, false);
    private final Issue third = new Issue(3, "Artem", "Item3", Collections.singleton("Teg3"), Collections.singleton("Федор"), 9_55, true);
    private final Issue fourth = new Issue(4, "Aleksandr", "Item4", Collections.singleton("Teg12"), Collections.singleton("Василий"), 17_00, false);
    private final Issue fifth = new Issue(5, "Maksim", "Item5", Collections.singleton("Teg543"), Collections.singleton("Михаил"), 19_00, false);

    @BeforeEach
    void shouldAd() {
        repo.save(first);
        repo.save(second);
        repo.save(third);
        repo.save(fourth);
        repo.saveAll(issues);
    }
    @Test
    void findById(){
        Issue actual=repo.findById(3);
        Issue expected=third;
        assertEquals(actual,expected);
    }

}