package com.report.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.ReportDao;
import com.report.entity.Report;
import com.report.entity.request.ReportSearchListRequest;
import com.report.entity.response.search.ReportPageResponse;
import com.report.utils.Constants;

@Repository
public class ReportDaoImpl extends AbstractBaseDao implements ReportDao{
    

    @Override
    public Report getReportById(Integer reportId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Report ");
        sql.append(" WHERE ");
        sql.append("    reportId = :reportId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("reportId", reportId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        
        Report result = null;
        try {
            result = (Report) query.getSingleResult();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public Report add(Report entity) {
        this.getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public Report update(Report entity) {
        this.getEntityManager().merge(entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Report> getReportByIssue(Integer issueId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Report ");
        sql.append(" WHERE ");
        sql.append("    issueId = :issueId");
        sql.append("    AND delFlg = :delFlg ORDER BY reportId DESC");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("issueId", issueId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        
        List<Report> result = null;
        try {
            result = (List<Report>) query.getResultList();
        } catch (NoResultException e) {
        }
        return result;
    }

    @Override
    public ReportPageResponse findAll(ReportSearchListRequest searchListRequest) {
        return super.findAll(searchListRequest);
    }

}
