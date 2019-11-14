package com.report.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.TaskDao;
import com.report.entity.Task;
import com.report.entity.response.TaskPageResponse;
import com.report.entity.response.search.TaskSearchListRequest;
import com.report.utils.Constants;

@Repository
public class TaskDaoImpl extends AbstractBaseDao implements TaskDao {

    private static final Logger log = LoggerFactory.getLogger(TaskDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Task> getTasksByTeam(Integer teamId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Task ");
        sql.append(" WHERE ");
        sql.append("    teamId = :teamId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("teamId", teamId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Task> result = null;
        result = (List<Task>) query.getResultList();
        return result;
    }

    @Override
    public Task getTaskById(Integer taskId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Task ");
        sql.append(" WHERE ");
        sql.append("    taskId = :taskId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("taskId", taskId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        Task result = null;
        try {
            result = (Task) query.getSingleResult();
        } catch (NoResultException e) {
            log.debug("Record that does not exist.");
        }
        return result;
    }

    @Override
    public void add(Task task) {
        this.getEntityManager().persist(task);
    }

    @Override
    public void update(Task task) {
        this.getEntityManager().merge(task);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Task> getTasksByUser(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Task ");
        sql.append(" WHERE ");
        sql.append("    userId = :userId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Task> result = null;
        result = (List<Task>) query.getResultList();
        return result;
    }

    @Override
    public TaskPageResponse findAll(TaskSearchListRequest searchListRequest) {
        TaskPageResponse pageResponse = super.findAll(searchListRequest);
        return pageResponse;
    }

}
