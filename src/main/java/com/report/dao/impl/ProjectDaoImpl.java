package com.report.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.ProjectDao;
import com.report.entity.Project;
import com.report.entity.ProjectUser;
import com.report.entity.request.ProjectSearchListRequest;
import com.report.entity.response.search.ProjectPageResponse;
import com.report.utils.Constants;

@Repository
public class ProjectDaoImpl extends AbstractBaseDao implements ProjectDao {

    @Override
    public Project getProjectById(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Project ");
        sql.append(" WHERE ");
        sql.append("    projectId = :projectId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        Project result = null;
        try {
            result = (Project) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public Project add(Project entity) {
        this.getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public Project update(Project entity) {
        this.getEntityManager().merge(entity);
        return entity;
    }

    @Override
    public ProjectPageResponse findAll(ProjectSearchListRequest listRequest) {
        ProjectPageResponse pageResponse = super.findAll(listRequest);
        return pageResponse;
    }

    @Override
    public void addProjectUser(ProjectUser entity) {
        this.getEntityManager().persist(entity);
    }

    @Override
    public void updateProjectUser(Integer userId, String lastmodified, String lastmodifiedbyUsername) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ");
        sql.append(" ProjectUser ");
        sql.append(" SET ");
        sql.append("    lastmodified = :lastmodified, ");
        sql.append("    delFlg = :delFlg, ");
        sql.append("    lastmodifiedbyUsername = :lastmodifiedbyUsername ");
        sql.append(" WHERE ");
        sql.append("    userId = :userId ");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_1);
        query.setParameter("lastmodified", lastmodified);
        query.setParameter("lastmodifiedbyUsername", lastmodifiedbyUsername);
        query.executeUpdate();
    }

    @Override
    public ProjectUser getLeaderOld(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tu ");
        sql.append(" FROM ProjectUser tu INNER JOIN UserRole ur ON tu.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    tu.delFlg = :delFlg");
        sql.append("    AND tu.projectId = :projectId");
        sql.append("    AND r.name = :role");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "LEADER");
        query.setParameter("projectId", projectId);

        ProjectUser result = null;
        try {
            result = (ProjectUser) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public void delProjectUser(Integer userId, Integer projectId, String lastmodified, String lastmodifiedbyUsername) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ");
        sql.append(" ProjectUser ");
        sql.append(" SET ");
        sql.append("    lastmodified = :lastmodified, ");
        sql.append("    lastmodifiedbyUsername = :lastmodifiedbyUsername, ");
        sql.append("    delFlg = :delFlg ");
        sql.append(" WHERE ");
        sql.append("    userId = :userId AND projectId = :projectId");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_1);
        query.setParameter("lastmodified", lastmodified);
        query.setParameter("lastmodifiedbyUsername", lastmodifiedbyUsername);
        query.setParameter("projectId", projectId);
        query.executeUpdate();
    }

    @Override
    public void updateProjectUser(ProjectUser entity) {
        this.getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    public List<Project> getProjects(Boolean isAdmin, Integer userIdByToken) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT(p) ");
        sql.append(" FROM Project p INNER JOIN ProjectUser pu ON p.projectId = pu.projectId");
        sql.append(" WHERE ");
        sql.append("    p.delFlg = :delFlg");
        if (isAdmin == false) {
            sql.append(" AND pu.userId = :userIdByToken");
        }

        Query query = this.getEntityManager().createQuery(sql.toString());
        if (isAdmin == false) {
            query.setParameter("userIdByToken", userIdByToken);
        }

        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Project> result = null;
        try {
            result = (List<Project>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

}
