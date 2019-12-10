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
import com.report.service.ProjectService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class ProjectController {

    @Resource
    private ProjectService teamService;
    
    
    @RequestMapping(value = "/api/project/{project_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProjectById(@PathVariable Integer project_id) {
        ResultBean resultBean = null;
        resultBean = teamService.getProjectById(project_id);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/projects", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProjects(HttpServletRequest request, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = teamService.getProjects(request, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/projects/add-issue", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getProjectsAddIssue(HttpServletRequest request) {
        ResultBean resultBean = null;
        resultBean = teamService.getProjects(request);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    
    @RequestMapping(value = "/api/project", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addProject(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = teamService.addProject(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/project", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateProject(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = teamService.updateProject(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
