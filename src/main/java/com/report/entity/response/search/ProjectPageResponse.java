package com.report.entity.response.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.PageResponse;
import com.report.entity.response.ProjectResponse;

public class ProjectPageResponse extends PageResponse<ProjectResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<ProjectResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<ProjectResponse> responses = new ArrayList<ProjectResponse>();
        ProjectResponse response;
        for (Object rawResult : rawResults) {
            response = new ProjectResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}
