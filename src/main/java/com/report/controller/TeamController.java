package com.report.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.entity.ResultBean;
import com.report.service.TeamService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class TeamController {

    @Resource
    private TeamService teamService;
    
    
    @RequestMapping(value = "/api/team/{team_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTeamById(@PathVariable Integer team_id) {
        ResultBean resultBean = null;
        resultBean = teamService.getTeamById(team_id);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/teams", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTeams(HttpServletRequest request, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = teamService.getTeams(request, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    

//    @RequestMapping(value = "/api/teams/user/{user_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
//    public ResponseEntity<ResultBean> getTeamsById(@PathVariable Integer user_id, String name, String date, Integer page, Integer size) {
//        ResultBean resultBean = null;
//        resultBean = teamService.getTeamsById(user_id, name, date, page, size);
//        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
//    }
    
    @RequestMapping(value = "/api/team", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addTeam(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = teamService.addTeam(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/team", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateTeam(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = teamService.updateTeam(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
