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
import com.report.entity.response.LeaderResponse;
import com.report.entity.response.UserPageResponse;
import com.report.entity.response.UserResponse;
import com.report.entity.response.UserRoleResponse;
import com.report.entity.response.search.UserSearchListRequest;
import com.report.utils.Constants;

@Repository
public class UserDaoIpml extends AbstractBaseDao implements UserDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<LeaderResponse> getLeaders() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.LeaderResponse( ");
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
        query.setParameter("role", "LEADER");

        List<LeaderResponse> result = null;
        try {
            result = (List<LeaderResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LeaderResponse> getUsersInTeam() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.LeaderResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN TeamUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.delFlg = :tudelFlg");
        sql.append("    AND tu.removed = :removed");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("tudelFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");
        query.setParameter("removed", Constants.DEL_FLG_0);

        List<LeaderResponse> result = null;
        try {
            result = (List<LeaderResponse>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LeaderResponse> getUsers() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.LeaderResponse( ");
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

        List<LeaderResponse> result = null;
        try {
            result = (List<LeaderResponse>) query.getResultList();
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> getListUserOldTeam(Integer teamId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("  u.userId ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN TeamUser tu ON tu.userId = ur.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.teamId = :teamId");
        sql.append("    AND tu.removed = :removed");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("removed", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");
        query.setParameter("teamId", teamId);

        List<Integer> result = null;
        try {
            result = (List<Integer>) query.getResultList();
        } catch (NoResultException e) {
        }
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
    public List<UserResponse> getUsersByTeam(Integer team_id) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.UserResponse( ");
        sql.append("  u.userId, ");
        sql.append("  u.fullname, ");
        sql.append("  u.email, ");
        sql.append("  tu.removed) ");
        sql.append(" FROM User u INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append("      INNER JOIN TeamUser tu ON tu.userId = u.userId ");
        sql.append(" WHERE ");
        sql.append("    u.delFlg = :delFlg");
        sql.append("    AND r.name = :role");
        sql.append("    AND tu.removed = :removed");
        sql.append("    AND tu.teamId = :teamId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "MEMBER");
        query.setParameter("removed", Constants.DEL_FLG_0);
        query.setParameter("teamId", team_id);

        List<UserResponse> result = null;
        try {
            result = (List<UserResponse>) query.getResultList();
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

}
