package com.report.dao;


import java.util.List;

import com.report.entity.Project;
import com.report.entity.ProjectUser;
import com.report.entity.request.ProjectSearchListRequest;
import com.report.entity.response.search.ProjectPageResponse;

public interface ProjectDao {

    public Project getProjectById(Integer projectId);

    public Project add(Project entity);

    public Project update(Project entity);


    public void addProjectUser(ProjectUser projectUser);
    
    public void updateProjectUser(Integer userId, String string, String string2);


    ProjectPageResponse findAll(ProjectSearchListRequest listRequest);

    public ProjectUser getLeaderOld(Integer projectId);

    public void delProjectUser(Integer i, Integer projectId, String localDateTime, String userNameByToken);

    public void updateProjectUser(ProjectUser projectUser);

    public List<Project> getProjects(Boolean isAdmin, Integer userIdByToken);
}
