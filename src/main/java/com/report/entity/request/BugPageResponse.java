package com.report.entity.request;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.BugResponse;
import com.report.entity.response.PageResponse;

public class BugPageResponse extends PageResponse<BugResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<BugResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<BugResponse> responses = new ArrayList<BugResponse>();
        BugResponse response;
        for (Object rawResult : rawResults) {
            response = new BugResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}
