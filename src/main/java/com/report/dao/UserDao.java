package com.report.dao;

import java.util.List;

import com.report.entity.Role;
import com.report.entity.User;
import com.report.entity.UserRole;
import com.report.entity.request.UserSearchListRequest;
import com.report.entity.response.ManagerResponse;
import com.report.entity.response.UserResponse;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.UserPageResponse;

public interface UserDao {
    public List<ManagerResponse> getManagers();
    public List<ManagerResponse> getUsers();
    public User findOne(String email);
    public User getUser(Integer id);
    public List<UserRoleResponse> getUserRoleByUserId(Integer userId);
    public List<Role> getRoles(Integer userId);
    public boolean checkMailExits(String email);
    public void add(User entity);
    public void update(User entity);
    public void addUserRole(UserRole userRole);
    public UserRole getUserRole(Integer userId);
    public void updateUserRole(UserRole userRole);
    public UserPageResponse findAll(UserSearchListRequest searchListRequest);
    public List<UserResponse> getUsersByProject(Integer projectId);
    public List<UserResponse> getManByProject(Integer projectId);
    public List<Integer> getUsersOldByProject(Integer projectId);
    public List<Integer> getManOldByProject(Integer projectId);
    public List<ManagerResponse> getUsersAddProject();
    public UserResponse getUserById(Integer userId);
}
