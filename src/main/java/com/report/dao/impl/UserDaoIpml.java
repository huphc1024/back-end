package com.report.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.UserDao;
import com.report.entity.Role;
import com.report.entity.User;
import com.report.entity.UserRole;
import com.report.entity.request.UserSearchListRequest;
import com.report.entity.response.ManagerResponse;
import com.report.entity.response.UserResponse;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.UserPageResponse;
import com.report.utils.Constants;

@Repository
public class UserDaoIpml extends AbstractBaseDao implements UserDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<ManagerResponse> getManagers() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.ManagerResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MANAGER");

        List<ManagerResponse> result = null;
        try {
            result = (List<ManagerResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ManagerResponse> getUsers() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.ManagerResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");

        List<ManagerResponse> result = null;
        try {
            result = (List<ManagerResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public User findOne(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM User u ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND u.email = :email");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("email", email);

        User result = null;
        try {
            result = (User) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getRoles(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM Role u INNER JOIN UserRole ur ON ur.roleId = u.roleId ");
        sql.append(" WHERE ");
        sql.append("     ur.userId = :userId");
        sql.append("    AND ur.delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Role> result = null;
        try {
            result = (List<Role>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public boolean checkMailExits(String email) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(u) ");
        sql.append(" FROM User u");
        sql.append(" WHERE ");
        sql.append("     u.email = :email");
        sql.append("    AND u.delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("email", email);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        Long result = null;
        try {
            result = (Long) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result > 0 ? true : false;
    }

    @Override
    public void add(User entity) {
        this.getEntityManager().persist(entity);
    }

    @Override
    public void update(User entity) {
        this.getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserRoleResponse> getUserRoleByUserId(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.UserRoleResponse( ");
        sql.append("     r.name");
        sql.append(" )");
        sql.append(" FROM UserRole u INNER JOIN Role r ON u.roleId = r.roleId ");
        sql.append("    INNER JOIN User us ON us.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    us.userId = :userId");
        sql.append("    AND us.delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<UserRoleResponse> result = null;
        result = query.getResultList();
        return result;
    }

    @Override
    public void addUserRole(UserRole userRole) {
        this.getEntityManager().merge(userRole);
    }

    @Override
    public UserRole getUserRole(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM UserRole u INNER JOIN Role r ON u.roleId = r.roleId ");
        sql.append("    INNER JOIN User us ON us.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    us.userId = :userId");
        sql.append("    AND us.delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        UserRole result = null;
        result = (UserRole) query.getSingleResult();
        return result;
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        this.getEntityManager().persist(userRole);
    }

    @Override
    public UserPageResponse findAll(UserSearchListRequest searchListRequest) {
        return super.findAll(searchListRequest);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserResponse> getUsersByProject(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.UserResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN ProjectUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND tu.delFlg = :tudelFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.projectId = :projectId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("tudelFlg", Constants.DEL_FLG_0);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");
        query.setParameter("projectId", projectId);

        List<UserResponse> result = null;
        try {
            result = (List<UserResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public List<Integer> getUsersOldByProject(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("  u.userId ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN ProjectUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND tu.delFlg = :tudelFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.projectId = :projectId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("tudelFlg", Constants.DEL_FLG_0);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");
        query.setParameter("projectId", projectId);

        List<Integer> result = null;
        try {
            result = (List<Integer>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public User getUser(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT u ");
        sql.append(" FROM User u ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND u.userId = :userId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("userId", id);

        User result = null;
        try {
            result = (User) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserResponse> getManByProject(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.UserResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN ProjectUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND tu.delFlg = :tudelFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.projectId = :projectId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("tudelFlg", Constants.DEL_FLG_0);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MANAGER");
        query.setParameter("projectId", projectId);

        List<UserResponse> result = null;
        try {
            result = (List<UserResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getManOldByProject(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("  u.userId ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN ProjectUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND tu.delFlg = :tudelFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.projectId = :projectId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("tudelFlg", Constants.DEL_FLG_0);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MANAGER");
        query.setParameter("projectId", projectId);

        List<Integer> result = null;
        try {
            result = (List<Integer>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ManagerResponse> getUsersAddProject() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.ManagerResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");

        List<ManagerResponse> result = null;
        try {
            result = (List<ManagerResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public UserResponse getUserById(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.UserResponse( ");
        sql.append("    u.userId,");
        sql.append("    u.fullname,");
        sql.append("    u.email,");
        sql.append("    u.created,");
        sql.append("    u.createdbyUsername,");
        sql.append("    u.lastmodified,");
        sql.append("    u.lastmodifiedbyUsername,");
        sql.append("    r.name)");
        sql.append(" FROM User u ");
        sql.append("  INNER JOIN UserRole ur ON ur.userId = u.userId INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND u.userId = :userId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("userId", userId);

        UserResponse result = null;
        try {
            result = (UserResponse) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

}
