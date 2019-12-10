package com.report.entity.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReportResponse implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @JsonProperty("report_id")
    private Integer reportId;

    @JsonProperty("issue_id")
    private Integer issueId;

    @JsonProperty("content")
    private String content;

    @JsonProperty("created")
    private String created;

    @JsonProperty("createdby")
    private String createdby;

    @JsonProperty("lastmodified")
    private String lastmodified;

    @JsonProperty("lastmodifiedby")
    private String lastmodifiedby;

    @JsonProperty("del_flg")
    private String delFlg;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getIssueId() {
        return issueId;
    }

    public void setIssueId(Integer issueId) {
        this.issueId = issueId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(String lastmodified) {
        this.lastmodified = lastmodified;
    }

    public String getLastmodifiedby() {
        return lastmodifiedby;
    }

    public void setLastmodifiedby(String lastmodifiedby) {
        this.lastmodifiedby = lastmodifiedby;
    }

    public String getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(String delFlg) {
        this.delFlg = delFlg;
    }

    public ReportResponse(Integer reportId, Integer issueId, String content, String created, String createdby, String lastmodified, String lastmodifiedby,
            String delFlg) {
        super();
        this.reportId = reportId;
        this.issueId = issueId;
        this.content = content;
        this.created = created;
        this.createdby = createdby;
        this.lastmodified = lastmodified;
        this.lastmodifiedby = lastmodifiedby;
        this.delFlg = delFlg;
    }

    public ReportResponse() {
        super();
        // TODO Auto-generated constructor stub
    }

    

}
