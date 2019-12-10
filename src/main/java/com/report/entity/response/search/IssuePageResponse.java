package com.report.entity.response.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.IssueResponse;
import com.report.entity.response.PageResponse;

public class IssuePageResponse extends PageResponse<IssueResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<IssueResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<IssueResponse> responses = new ArrayList<IssueResponse>();
        IssueResponse response;
        for (Object rawResult : rawResults) {
            response = new IssueResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}
