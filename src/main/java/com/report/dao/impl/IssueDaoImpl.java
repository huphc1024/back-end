package com.report.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.IssueDao;
import com.report.entity.Issue;
import com.report.entity.request.IssueSearchListRequest;
import com.report.entity.response.IssueResponse;
import com.report.entity.response.search.IssuePageResponse;
import com.report.utils.Constants;

@Repository
public class IssueDaoImpl extends AbstractBaseDao implements IssueDao {

    private static final Logger log = LoggerFactory.getLogger(IssueDaoImpl.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Issue> getIssueByProject(Integer projectId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Issue ");
        sql.append(" WHERE ");
        sql.append("    projectId = :projectId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("projectId", projectId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Issue> result = null;
        result = (List<Issue>) query.getResultList();
        return result;
    }

    @Override
    public Issue getIssueById(Integer issueId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t ");
        sql.append(" FROM Issue t INNER JOIN Project p ON p.projectId = t.projectId");
        sql.append("     INNER JOIN User u ON u.userId = t.userId");
        sql.append(" WHERE t.delFlg = 0 AND u.delFlg = 0 AND p.delFlg = 0 AND t.issueId = :issueId ");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("issueId", issueId);

        Issue result = null;
        try {
            result = (Issue) query.getSingleResult();
        } catch (NoResultException e) {
            log.debug("Record that does not exist.");
        }
        return result;
    }

    @Override
    public void add(Issue entity) {
        this.getEntityManager().persist(entity);
    }

    @Override
    public void update(Issue entity) {
        this.getEntityManager().merge(entity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Issue> getIssueByUser(Integer userId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Issue ");
        sql.append(" WHERE ");
        sql.append("    userId = :userId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("userId", userId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);

        List<Issue> result = null;
        result = (List<Issue>) query.getResultList();
        return result;
    }

    @Override
    public IssuePageResponse findAll(IssueSearchListRequest searchListRequest) {
        IssuePageResponse pageResponse = super.findAll(searchListRequest);
        return pageResponse;
    }

    @Override
    public IssueResponse getDetailIssue(Integer issueId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT new com.report.entity.response.IssueResponse( ");
        sql.append("     t.issueId, ");
        sql.append("     t.userId, ");
        sql.append("     u.fullname, ");
        sql.append("     t.projectId, ");
        sql.append("     p.name, ");
        sql.append("     t.name, ");
        sql.append("     t.description, ");
        sql.append("     t.startDate, ");
        sql.append("     t.finishDate, ");
        sql.append("     t.done, ");
        sql.append("     t.workType, ");
        sql.append("     t.status, ");
        sql.append("     t.created, ");
        sql.append("     t.createdbyUsername, ");
        sql.append("     t.lastmodified, ");
        sql.append("     t.lastmodifiedbyUsername, ");
        sql.append("     t.delFlg) ");
        sql.append(" FROM Issue t INNER JOIN Project p ON p.projectId = t.projectId");
        sql.append("     INNER JOIN User u ON u.userId = t.userId");
        sql.append(" WHERE t.delFlg = 0 AND u.delFlg = 0 AND p.delFlg = 0 AND t.issueId = :issueId ");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("issueId", issueId);

        IssueResponse result = null;
        try {
            result = (IssueResponse) query.getSingleResult();
        } catch (NoResultException e) {
            log.debug("Record that does not exist.");
        }
        return result;
    }

}
