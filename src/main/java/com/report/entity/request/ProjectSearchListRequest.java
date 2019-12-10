package com.report.entity.request;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Project;
import com.report.entity.response.search.ProjectPageResponse;

public class ProjectSearchListRequest extends PageRequest {

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
                case "userId":
                    sqlWhere.append(String.format(" AND tu.userId = :%s ", k));
                    break;
                case "tuDelFlg":
                    sqlWhere.append(String.format(" AND tu.delFlg = :%s ", k));
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
        sql.append(" SELECT DISTINCT new com.report.entity.response.ProjectResponse( ");
        sql.append("    t.projectId, ");
        sql.append("    t.name, ");
        sql.append("    t.description, ");
        sql.append("    t.active, ");
        sql.append("    t.created, ");
        sql.append("    t.createdbyUsername, ");
        sql.append("    t.lastmodified, ");
        sql.append("    t.lastmodifiedbyUsername, ");
        sql.append("    t.delFlg ) ");
        sql.append(" FROM Project t INNER JOIN ProjectUser tu ON tu.projectId = t.projectId ");
        appendCondition(sql);
        sql.append(getSqlSort("t"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(DISTINCT t) FROM Project t INNER JOIN ProjectUser tu ON tu.projectId = t.projectId");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Project> getEntityClass() {
        return Project.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<ProjectPageResponse> getResponseClass() {
        return ProjectPageResponse.class;
    }

}
