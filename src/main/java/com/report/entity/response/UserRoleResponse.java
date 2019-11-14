package com.report.entity.response;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRoleResponse implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @JsonProperty("user_id")
    private Integer userId;

    // Role
    @JsonProperty("role")
    private String role;

    public UserRoleResponse() {

    }

    /**
     * @param userId
     * @param role
     */
    public UserRoleResponse(Integer userId, String role) {
        super();
        this.userId = userId;
        this.role = role;
    }

    public UserRoleResponse(String role) {
        this.role = role;
    }

    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this)
            return true;
        if (!(o instanceof UserRoleResponse)) {
            return false;
        }

        UserRoleResponse oRole = (UserRoleResponse) o;

        return oRole.getRole().equals(role);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + role.hashCode();
        return result;
    }
}
