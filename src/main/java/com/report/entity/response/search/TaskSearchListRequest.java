package com.report.entity.response.search;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Task;
import com.report.entity.request.PageRequest;
import com.report.entity.response.TaskPageResponse;

public class TaskSearchListRequest extends PageRequest {

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
                    sqlWhere.append(String.format(" AND t.created >= :%s ", k));
                    break;
                default:
                    if (v instanceof String) {
                        String value = (String) v;
                        if (value.startsWith("%") || value.endsWith("%")) {
                            sqlWhere.append(String.format(" AND t.%s LIKE :%s ", k, k));
                        } else {
                            sqlWhere.append(String.format(" AND t.%s = :%s ", k, k));
                        }
                    } else {
                        sqlWhere.append(String.format(" AND t.%s = :%s ", k, k));
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
        sql.append(" SELECT t ");
        sql.append(" FROM Task t ");
        appendCondition(sql);
        sql.append(getSqlSort("t"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(t) FROM Task t ");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Task> getEntityClass() {
        return Task.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<TaskPageResponse> getResponseClass() {
        return TaskPageResponse.class;
    }

}
