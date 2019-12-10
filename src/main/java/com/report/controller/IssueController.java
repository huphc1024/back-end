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
import com.report.service.IssueService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class IssueController {

    @Resource
    private IssueService taskService;

    @RequestMapping(value = "/api/issue/{issue_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTaskById(@PathVariable Integer issue_id) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.getIssueById(issue_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/issues/project/{project_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getIssuesByProject(@PathVariable Integer project_id, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = taskService.getIssuesByProject(project_id, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/issues", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getIssues(HttpServletRequest request, Integer project_id, String name, String date, String status, String work_type, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = taskService.getIssues(request, project_id, name, date, status, work_type, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/api/issue-detail/{issue_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getIssueDetail(@PathVariable Integer issue_id) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.getIssueDetail(issue_id);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/issues/user/{user_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getIssuesByUser(@PathVariable Integer user_id, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = taskService.getIssuesByUser(user_id, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/issue", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> add(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.add(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/issue", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> update(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.update(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
