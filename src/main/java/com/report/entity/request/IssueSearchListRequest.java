package com.report.entity.request;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Issue;
import com.report.entity.response.search.IssuePageResponse;
import com.report.utils.DataUtil;

public class IssueSearchListRequest extends PageRequest {

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
                case "pdelFlg":
                    sqlWhere.append(String.format(" AND p.delFlg = :%s ", k));
                    break;
                case "udelFlg":
                    sqlWhere.append(String.format(" AND u.delFlg = :%s ", k));
                    break;
                case "userIdM":
                    sqlWhere.append(String.format(" OR u.userId = :%s ", k));
                    break;
                default:
                    if (DataUtil.isNumeric(k.substring(1, k.length()-1))) {
                        sqlWhere.append(String.format(" OR p.projectId = :%s ", k));
                    } else {
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
        sql.append(" SELECT new com.report.entity.response.IssueResponse( ");
        sql.append("     t.issueId, ");
        sql.append("     t.userId, ");
        sql.append("     u.fullname, ");
        sql.append("     t.projectId, ");
        sql.append("     p.name, ");
        sql.append("     t.name, ");
        sql.append("     t.description, ");
        sql.append("     t.startDate, ");
        sql.append("     t.finishDate, ");
        sql.append("     t.done, ");
        sql.append("     t.workType, ");
        sql.append("     t.status, ");
        sql.append("     t.created, ");
        sql.append("     t.createdbyUsername, ");
        sql.append("     t.lastmodified, ");
        sql.append("     t.lastmodifiedbyUsername, ");
        sql.append("     t.delFlg) ");
        sql.append(" FROM Issue t INNER JOIN Project p ON p.projectId = t.projectId");
        sql.append("     INNER JOIN User u ON u.userId = t.userId");
        appendCondition(sql);
        sql.append(getSqlSort("t"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT COUNT(t) ");
        sql.append(" FROM Issue t INNER JOIN Project p ON p.projectId = t.projectId");
        sql.append("     INNER JOIN User u ON u.userId = t.userId");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Issue> getEntityClass() {
        return Issue.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<IssuePageResponse> getResponseClass() {
        return IssuePageResponse.class;
    }

}
