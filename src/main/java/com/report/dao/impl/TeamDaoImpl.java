package com.report.dao.impl;



import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.TeamDao;
import com.report.entity.Team;
import com.report.entity.TeamUser;
import com.report.entity.response.search.TeamPageResponse;
import com.report.entity.response.search.TeamSearchListRequest;
import com.report.utils.Constants;

@Repository
public class TeamDaoImpl extends AbstractBaseDao implements TeamDao{
    

    @Override
    public Team getTeamById(Integer teamId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Team ");
        sql.append(" WHERE ");
        sql.append("    teamId = :teamId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("teamId", teamId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        
        Team result = null;
        try {
            result = (Team) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public Team add(Team entity) {
        this.getEntityManager().persist(entity);
        return null;
    }

    @Override
    public Team update(Team entity) {
        this.getEntityManager().merge(entity);
        return null;
    }

    @Override
    public TeamPageResponse findAll(TeamSearchListRequest listRequest) {
        TeamPageResponse pageResponse = super.findAll(listRequest);
        return pageResponse;
    }

    @Override
    public void addTeamUser(TeamUser teamUser) {
        this.getEntityManager().persist(teamUser);
    }

    @Override
    public void updateTeamUser(Integer userId, String lastmodified, String lastmodifiedbyUsername) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ");
        sql.append(" TeamUser ");
        sql.append(" SET ");
        sql.append("    removed = :removed, ");
        sql.append("    lastmodified = :lastmodified, ");
        sql.append("    lastmodifiedbyUsername = :lastmodifiedbyUsername ");
        sql.append(" WHERE ");
        sql.append("    userId = :userId ");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("removed", Constants.DEL_FLG_1);
        query.setParameter("lastmodified", lastmodified);
        query.setParameter("lastmodifiedbyUsername", lastmodifiedbyUsername);
        query.executeUpdate();
    }

    @Override
    public TeamUser getLeaderOld(Integer teamId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT tu ");
        sql.append(" FROM TeamUser tu INNER JOIN UserRole ur ON tu.userId = ur.userId ");
        sql.append("      INNER JOIN Role r ON r.roleId = ur.roleId ");
        sql.append(" WHERE ");
        sql.append("    tu.delFlg = :delFlg");
        sql.append("    AND tu.teamId = :teamId");
        sql.append("    AND r.name = :role");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        query.setParameter("role", "LEADER");
        query.setParameter("teamId", teamId);

        TeamUser result = null;
        try {
            result = (TeamUser) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public void delTeamUser(Integer userId, String lastmodified, String lastmodifiedbyUsername) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE ");
        sql.append(" TeamUser ");
        sql.append(" SET ");
        sql.append("    lastmodified = :lastmodified ");
        sql.append("    lastmodifiedbyUsername = :lastmodifiedbyUsername ");
        sql.append("    delFlg = :delFlg ");
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
    public void updateTeamUser(TeamUser teamUser) {
        this.getEntityManager().merge(teamUser);
    }

}
