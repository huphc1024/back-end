package com.report.entity.response.search;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import com.report.entity.response.PageResponse;
import com.report.entity.response.TeamResponse;

public class TeamPageResponse extends PageResponse<TeamResponse> {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("rawtypes")
    @Override
    protected List<TeamResponse> parseResult(List rawResults) {
        if (CollectionUtils.isEmpty(rawResults)) {
            return null;
        }
        List<TeamResponse> responses = new ArrayList<TeamResponse>();
        TeamResponse response;
        for (Object rawResult : rawResults) {
            response = new TeamResponse();
            BeanUtils.copyProperties(rawResult, response);
            responses.add(response);
        }
        return responses;
    }
}
