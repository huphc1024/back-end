package com.report.entity.response.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.PageResponse;
import com.report.entity.response.ReportResponse;

public class ReportPageResponse extends PageResponse<ReportResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<ReportResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<ReportResponse> responses = new ArrayList<ReportResponse>();
        ReportResponse response;
        for (Object rawResult : rawResults) {
            response = new ReportResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}