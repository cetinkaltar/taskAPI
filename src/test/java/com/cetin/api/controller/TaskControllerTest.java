package com.cetin.api.controller;

import com.cetin.api.dao.TaskRepository;
import com.cetin.api.model.Task;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskControllerTest {

    protected MockMvc mvc;

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    private TaskController taskController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        setUp(taskController);
    }

    @Test
    public void getTask() throws Exception {

        Task task = getTestData();
        when(taskRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(task));
        String uri = "/task/1";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(taskRepository, times(1)).findById(1L);

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a ",
                content.trim().length() > 0);
    }

    @Test
    public void getTasks() throws Exception {
        List<Task> tasks = getListOfTestData();
        when(taskRepository.findAll()).thenReturn(tasks);
        String uri = "/task/all";
        MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)).andReturn();

        String content = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        verify(taskRepository, times(1)).findAll();

        Assert.assertEquals("failure - expected HTTP status 200", 200, status);
        Assert.assertTrue(
                "failure - expected HTTP response body to have a ",
                content.trim().length() > 0);
    }


    public List<Task> getListOfTestData() {

        List<Task> listOfStubData = new ArrayList<>();
        listOfStubData.add(getTestData());
        return listOfStubData;
    }

    public Task getTestData() {

        Task task1 = new Task();
        task1.setId(1);
        task1.setTask("task 1");
        return task1;
    }

    protected void setUp(TaskController controller) {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

}