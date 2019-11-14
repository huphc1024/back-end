package com.report.entity.response;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

public class TaskPageResponse extends PageResponse<TaskResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<TaskResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<TaskResponse> responses = new ArrayList<TaskResponse>();
        TaskResponse response;
        for (Object rawResult : rawResults) {
            response = new TaskResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}
