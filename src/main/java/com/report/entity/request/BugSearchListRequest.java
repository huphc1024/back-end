package com.report.entity.request;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Bug;

public class BugSearchListRequest extends PageRequest{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    protected void appendCondition(StringBuilder sql) {
        if (!MapUtils.isEmpty(this.searchFields)) {
            StringBuilder sqlWhere = new StringBuilder();
            this.searchFields.forEach((k, v) -> {
                switch (k) {
                case "created":
                    sqlWhere.append(String.format(" AND b.created >= :%s ", k));
                    break;
                default:
                    if (v instanceof String) {
                        String value = (String) v;
                        if (value.startsWith("%") || value.endsWith("%")) {
                            sqlWhere.append(String.format(" AND b.%s LIKE :%s ", k, k));
                        } else {
                            sqlWhere.append(String.format(" AND b.%s = :%s ", k, k));
                        }
                    } else {
                        sqlWhere.append(String.format(" AND b.%s = :%s ", k, k));
                    }
                }
            });
            if (sqlWhere.length() > 0) {
                sql.append(" WHERE 1=1 ").append(sqlWhere);
            }
        }
    }

    @Override
    public StringBuilder getQuery() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT b ");
        sql.append(" FROM Bug b ");
        appendCondition(sql);
        sql.append(getSqlSort("b"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(b) FROM Bug b ");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Bug> getEntityClass() {
        return Bug.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<BugPageResponse> getResponseClass() {
        return BugPageResponse.class;
    }

}
