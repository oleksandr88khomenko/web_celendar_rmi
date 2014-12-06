package com.diosoft.training.project.persistence.sequence;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SequenceDAOTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @Test
    public void testGetNextSequenceId() throws Exception {

        SequenceDAO sequenceDAO = mock(SequenceDAO.class);
        Long expSequence = 1L;
        when(sequenceDAO.getNextSequenceId("event")).thenReturn(2L);
        Long result = sequenceDAO.getNextSequenceId("event");
        Assert.assertNotSame(expSequence, result);


    }
}