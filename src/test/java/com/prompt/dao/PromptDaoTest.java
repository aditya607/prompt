package com.prompt.dao;

import com.prompt.model.Prompt;
import org.junit.Test;

import static org.junit.Assert.*;

public class PromptDaoTest {
    PromptDao promptDao=new PromptDao();

    @Test
    public void getPrompts() {
        Prompt prompt=promptDao.getPrompts("welcome");
        assertEquals("welcome",prompt.getName());
        prompt=promptDao.getPrompts("car");
        assertEquals(null,prompt.getName());
    }


    @Test
    public void addPrompt() {
        Prompt prompt=new Prompt();
        prompt.setId("welcome");
        assertFalse(promptDao.addPrompt(prompt));
        prompt.setId("rani");
        prompt.setName("rani kumari");
        assertTrue(promptDao.addPrompt(prompt));
        assertTrue(promptDao.deletePrompt("rani"));


    }

    @Test
    public void updatePrompt() {
        Prompt prompt=new Prompt();
        prompt.setId("rani");
        prompt.setName("rani kumari");
        assertTrue(promptDao.addPrompt(prompt));
        prompt.setName("rani ji");
        assertTrue(promptDao.updatePrompt(prompt));
        Prompt prompt1=promptDao.getPrompts("rani");
        assertEquals("rani ji",prompt1.getName());
        assertTrue(promptDao.deletePrompt("rani"));
    }

    @Test
    public void deletePrompt() {
        Prompt prompt=new Prompt();
        prompt.setId("raja");
        prompt.setName("raja babu");
        assertTrue(promptDao.addPrompt(prompt));
        assertTrue(promptDao.deletePrompt("raja"));
        Prompt prompt1=promptDao.getPrompts("raja");
        assertEquals(null,prompt1.getName());


    }

    @Test
    public void allPrompts() {
    }
}