package ua.bestlunch.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Виктор on 21.10.2016.
 */
//TODO CORRECT ALL TEST TO MATCHERS
public abstract class AbstractVoteServiceTest extends AbstractServiceTest{

    @Autowired
    protected VoteService service;

    @Test
    public void testSaveVote(){

    }

    @Test
    public void testSaveDuplicateVote(){

    }

    @Test
    public void testDelete(){

    }

    @Test
    public void testNotFoundDelete(){

    }

    @Test
    public void testNotAllowDelete(){

    }

    public void testFindVotesByUser(){

    }

    public void testFindVotesByRestaurant(){

    }
}
