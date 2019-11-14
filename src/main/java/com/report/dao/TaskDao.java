package com.report.dao;

import java.util.List;

import com.report.entity.Task;
import com.report.entity.response.TaskPageResponse;
import com.report.entity.response.search.TaskSearchListRequest;

public interface TaskDao {
    public List<Task> getTasksByTeam(Integer teamId);
    public List<Task> getTasksByUser(Integer userId);
    public Task getTaskById(Integer taskId);
    public void add(Task task);
    public void update(Task task);
    public TaskPageResponse findAll(TaskSearchListRequest searchListRequest);
}
