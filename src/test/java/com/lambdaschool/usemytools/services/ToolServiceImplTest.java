package com.lambdaschool.usemytools.services;

import com.lambdaschool.usemytools.usemytools.UsemytoolsApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsemytoolsApplication.class)

public class ToolServiceImplTest
{
    @Autowired
    private ToolService toolService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void findAll()
    {
        assertEquals(3, toolService.findAll().size());
    }

    @Test
    public void findAll1()
    {
    }

    @Test
    public void findToolById()
    {
    }

    @Test
    public void findToolByNameLike()
    {
    }

    @Test
    public void delete()
    {
    }

    @Test
    public void save()
    {
    }

    @Test
    public void savetoOwner()
    {
    }

    @Test
    public void savetoUser()
    {
    }

    @Test
    public void update()
    {
    }
}