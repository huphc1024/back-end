package com.report.entity.request;

import org.apache.commons.collections4.MapUtils;

import com.report.entity.Report;
import com.report.entity.response.search.ReportPageResponse;

public class ReportSearchListRequest extends PageRequest {

    private static final long serialVersionUID = 1L;


    @Override
    protected void appendCondition(StringBuilder sql) {
        if (!MapUtils.isEmpty(this.searchFields)) {
            StringBuilder sqlWhere = new StringBuilder();
            this.searchFields.forEach((k, v) -> {
                switch (k) {
                case "issueId":
                    sqlWhere.append(String.format(" AND r.issueId = :%s ", k));
                    break;
                default:
                    if (v instanceof String) {
                        String value = (String) v;
                        if (value.startsWith("%") || value.endsWith("%")) {
                            sqlWhere.append(String.format(" AND r.%s LIKE :%s ", k, k));
                        } else {
                            sqlWhere.append(String.format(" AND r.%s = :%s ", k, k));
                        }
                    } else {
                        sqlWhere.append(String.format(" AND r.%s = :%s ", k, k));
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
        sql.append(" SELECT r ");
        sql.append(" FROM Report r ");
        appendCondition(sql);
        sql.append(getSqlSort("r"));
        return sql;
    }

    @Override
    public StringBuilder getCount() {
        StringBuilder sql = new StringBuilder("SELECT COUNT(r) FROM Report r ");
        appendCondition(sql);
        return sql;
    }

    @Override
    public Class<Report> getEntityClass() {
        return Report.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<ReportPageResponse> getResponseClass() {
        return ReportPageResponse.class;
    }

}
