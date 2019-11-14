package com.report.dao;

import java.util.List;

import com.report.entity.Role;
import com.report.entity.User;
import com.report.entity.UserRole;
import com.report.entity.response.LeaderResponse;
import com.report.entity.response.UserPageResponse;
import com.report.entity.response.UserResponse;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.UserSearchListRequest;

public interface UserDao {
    public List<LeaderResponse> getLeaders();
    public List<LeaderResponse> getUsersInTeam();
    public List<LeaderResponse> getUsers();
    public User findOne(String email);
    public User getUser(Integer id);
    public List<Integer> getListUserOldTeam(Integer team_id);
    public List<UserRoleResponse> getUserRoleByUserId(Integer userId);
    public List<Role> getRoles(Integer userId);
    public boolean checkMailExits(String email);
    public void add(User entity);
    public void update(User entity);
    public void addUserRole(UserRole userRole);
    public UserRole getUserRole(Integer userId);
    public void updateUserRole(UserRole userRole);
    public UserPageResponse findAll(UserSearchListRequest searchListRequest);
    public List<UserResponse> getUsersByTeam(Integer team_id);
}
