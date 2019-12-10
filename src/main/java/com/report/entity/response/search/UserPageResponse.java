package com.report.entity.response.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.PageResponse;
import com.report.entity.response.UserResponse;

public class UserPageResponse extends PageResponse<UserResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<UserResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<UserResponse> responses = new ArrayList<UserResponse>();
        UserResponse response;
        for (Object rawResult : rawResults) {
            response = new UserResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}