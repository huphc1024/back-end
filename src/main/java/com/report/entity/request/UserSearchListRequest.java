package com.report.entity.request;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.User;
import com.report.entity.response.search.UserPageResponse;

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
        sql.append(" SELECT new com.report.entity.response.UserResponse( ");
        sql.append("    u.userId,");
        sql.append("    u.fullname,");
        sql.append("    u.email,");
        sql.append("    u.created,");
        sql.append("    u.createdbyUsername,");
        sql.append("    u.lastmodified,");
        sql.append("    u.lastmodifiedbyUsername,");
        sql.append("    r.name)");
        sql.append(" FROM User u ");
        sql.append("  INNER JOIN UserRole ur ON ur.userId = u.userId INNER JOIN Role r ON r.roleId = ur.roleId ");
        appendCondition(sql);
        sql.append(getSqlSort("u"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(u) FROM User u ");
        sql.append("  INNER JOIN UserRole ur ON ur.userId = u.userId INNER JOIN Role r ON r.roleId = ur.roleId ");
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
