package com.report.dao;

import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.request.PageRequest;
import com.report.entity.response.PageResponse;

public abstract class AbstractBaseDao {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext()
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T extends PageResponse> T findAll(PageRequest searchDTO) {
        String hsql = searchDTO.getQuery().toString().trim();
        Query query = this.entityManager.createQuery(hsql);
        if (searchDTO.hasPaging()) {
            query.setFirstResult(searchDTO.getFirstResult()).setMaxResults(searchDTO.getNoRecordInPage());
        }
        fillParams(query, searchDTO.getSearchFields());
        T instance;
        try {
            instance = (T) searchDTO.getResponseClass().getConstructor().newInstance();
            instance.pageInfo(searchDTO.getCurrentPage(), searchDTO.getNoRecordInPage(), countTotalRecord(searchDTO)).rawResults(query.getResultList());
            return instance;
        } catch (Exception e) {
            return null;
        }

    }

    protected Long countTotalRecord(PageRequest searchDTO) {
        Query query = this.entityManager.createQuery(searchDTO.getCount().toString());
        fillParams(query, searchDTO.getSearchFields());
        Object singResult = query.getSingleResult();
        if (Objects.isNull(singResult)) {
            return (long) 0;
        }
        return ((Long) singResult);
    }

    private void fillParams(Query query, Map<String, Object> searchParams) {
        if (MapUtils.isEmpty(searchParams)) {
            return;
        }
        searchParams.forEach((k, v) -> query.setParameter(k, v));
    }
}
