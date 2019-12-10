package com.report.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.report.dao.AbstractBaseDao;
import com.report.dao.BugDao;
import com.report.entity.Bug;
import com.report.entity.request.BugPageResponse;
import com.report.entity.request.BugSearchListRequest;
import com.report.utils.Constants;

@Repository
public class BugDaoImpl extends AbstractBaseDao implements BugDao{
    
    private static final Logger log = LoggerFactory.getLogger(BugDaoImpl.class);

    @Override
    public Bug getBugById(Integer bugId) {
        log.info("### getBugById() START ###");
        StringBuilder sql = new StringBuilder();
        sql.append(" FROM Bug ");
        sql.append(" WHERE ");
        sql.append("    bugId = :bugId");
        sql.append("    AND delFlg = :delFlg");

        Query query = this.getEntityManager().createQuery(sql.toString());
        query.setParameter("bugId", bugId);
        query.setParameter("delFlg", Constants.DEL_FLG_0);
        
        Bug result = null;
        try {
            result = (Bug) query.getSingleResult();
        } catch (NoResultException e) {
            log.debug("Record that does not exist.");
        }
        log.info("### getBugById() END ###");
        return result;
    }

    @Override
    public Bug add(Bug entity) {
        log.info("### add() START ###");
        this.getEntityManager().persist(entity);
        log.info("### add() END ###");
        return null;
    }

    @Override
    public Bug update(Bug entity) {
        log.info("### update() START ###");
        this.getEntityManager().merge(entity);
        log.info("### update() END ###");
        return null;
    }

    @Override
    public BugPageResponse findAll(BugSearchListRequest searchListRequest) {
        return super.findAll(searchListRequest);
    }

}
