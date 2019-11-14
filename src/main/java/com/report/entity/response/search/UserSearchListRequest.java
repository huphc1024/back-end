package com.report.entity.response.search;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.User;
import com.report.entity.request.PageRequest;
import com.report.entity.response.UserPageResponse;

public class UserSearchListRequest extends PageRequest {

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
                    sqlWhere.append(String.format(" AND u.created >= :%s ", k));
                    break;
                default:
                    if (v instanceof String) {
                        String value = (String) v;
                        if (value.startsWith("%") || value.endsWith("%")) {
                            sqlWhere.append(String.format(" AND u.%s LIKE :%s ", k, k));
                        } else {
                            sqlWhere.append(String.format(" AND u.%s = :%s ", k, k));
                        }
                    } else {
                        sqlWhere.append(String.format(" AND u.%s = :%s ", k, k));
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
        sql.append(" SELECT u ");
        sql.append(" FROM User u ");
        appendCondition(sql);
        sql.append(getSqlSort("u"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(u) FROM User u ");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<UserPageResponse> getResponseClass() {
        return UserPageResponse.class;
    }

}
