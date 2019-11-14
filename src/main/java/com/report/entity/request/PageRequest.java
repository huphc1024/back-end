package com.report.entity.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.response.PageResponse;
import com.report.utils.DataUtil;

public abstract class PageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    public Integer noRecordInPage;
    public Integer currentPage;
    protected List<String> sortFields;
    protected Map<String, Object> searchFields;

    public PageRequest addSearchField(String key, Object value, boolean... isNotUsingLike) {
        if (this.searchFields == null) {
            this.searchFields = new HashMap<>();
        }
        if (Objects.isNull(value))
            return this;
        if (value instanceof String) {
            String trimmedValue = ((String) value).trim();
            if (trimmedValue.length() == 0)
                return this;
            if (isNotUsingLike.length == 0 || !isNotUsingLike[0]) {
                value = "%" + String.valueOf(value).replaceAll("_", "\\\\_").replaceAll("%", "\\\\%") + "%";
            }
        }
        this.searchFields.put(key, value);
        return this;
    }

    public PageRequest addsSortFields(List<String> sortFields) {
        if (Objects.isNull(sortFields)) {
            sortFields = new ArrayList<String>();
        }
        this.sortFields.addAll(sortFields);
        return this;
    }

    public PageRequest addsSortField(String sortField) {
        if (Objects.isNull(sortFields)) {
            sortFields = new ArrayList<String>();
        }
        if (DataUtil.isEmpty(sortField)) {
            return this;
        }
        this.sortFields.add(sortField);
        return this;
    }

    public StringBuilder getSqlWhere(String alias) {
        StringBuilder sqlWhere = new StringBuilder();
        if (!MapUtils.isEmpty(this.searchFields)) {
            sqlWhere.append(" WHERE ");
            StringBuilder hsql = sqlWhere;
            this.searchFields.forEach((k, v) -> hsql.append(String.format(alias + ".%s = :%s AND ", k, k)));
            sqlWhere = sqlWhere.delete(sqlWhere.length() - 4, sqlWhere.length());
        }
        return sqlWhere;
    }

    public StringBuilder getSqlSort(String alias) {
        StringBuilder sqlSort = new StringBuilder();
        if (DataUtil.isEmpty(this.sortFields)) {
            return sqlSort;
        }
        sqlSort.append(" ORDER BY ");
        for (String sortField : sortFields) {
            if (DataUtil.isEmpty(alias)) {
                sqlSort.append(sortField + ", ");
            } else {
                sqlSort.append(alias + "." + sortField + ", ");
            }
        }
        return sqlSort.delete(sqlSort.length() - 2, sqlSort.length());

    }

    public PageRequest noRecordInPage(Integer noRecordInPage) {
        this.noRecordInPage = noRecordInPage;
        return this;
    }

    public PageRequest currentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    protected abstract void appendCondition(StringBuilder sql);

    public abstract StringBuilder getQuery();

    public abstract StringBuilder getCount();

    @SuppressWarnings("rawtypes")
    public abstract Class getEntityClass();

    @SuppressWarnings("rawtypes")
    public abstract <T extends PageResponse> Class<T> getResponseClass();

    public boolean hasPaging() {
        return this.currentPage != null || this.noRecordInPage != null;
    }

    public Integer getFirstResult() {
        return (currentPage - 1) * noRecordInPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getNoRecordInPage() {
        return noRecordInPage;
    }

    public Map<String, Object> getSearchFields() {
        return searchFields;
    }
}
