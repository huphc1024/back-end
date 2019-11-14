package com.report.entity.response.search;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Team;
import com.report.entity.request.PageRequest;

public class TeamSearchListRequest extends PageRequest {

    private static final long serialVersionUID = 1L;


    @Override
    protected void appendCondition(StringBuilder sql) {
        if (!MapUtils.isEmpty(this.searchFields)) {
            StringBuilder sqlWhere = new StringBuilder();
            this.searchFields.forEach((k, v) -> {
                switch (k) {
                case "name":
                    sqlWhere.append(String.format(" AND t.name LIKE :%s ", k));
                    break;
                case "created":
                    sqlWhere.append(String.format(" AND t.created >= :%s ", k));
                    break;
                case "role":
                    sqlWhere.append(String.format(" AND r.name = :%s ", k));
                    break;
                case "userId":
                    sqlWhere.append(String.format(" AND tu.userId = :%s ", k));
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
        sql.append(" SELECT new com.report.entity.response.TeamResponse( ");
        sql.append("    t.teamId, ");
        sql.append("    t.name, ");
        sql.append("    u.email, ");
        sql.append("    t.active, ");
        sql.append("    t.created, ");
        sql.append("    t.createdbyUsername, ");
        sql.append("    t.lastmodified, ");
        sql.append("    t.lastmodifiedbyUsername, ");
        sql.append("    tu.removed ) ");
        sql.append(" FROM Team t INNER JOIN TeamUser tu ON tu.teamId = t.teamId ");
        sql.append("    INNER JOIN User u ON tu.userId = u.userId ");
        sql.append("    INNER JOIN UserRole ur ON u.userId = ur.userId ");
        sql.append("    INNER JOIN Role r ON ur.roleId = r.roleId ");
        appendCondition(sql);
        sql.append(getSqlSort("t"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(t) FROM Team t INNER JOIN TeamUser tu ON tu.teamId = t.teamId"
                + " INNER JOIN User u ON tu.userId = u.userId INNER JOIN UserRole ur ON u.userId = ur.userId"
                + " INNER JOIN Role r ON ur.roleId = r.roleId ");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<TeamPageResponse> getResponseClass() {
        return TeamPageResponse.class;
    }

}
