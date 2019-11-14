package com.report.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.report.entity.ResultBean;
import com.report.service.BugService;

@Controller
public class BugController {

    @Resource
    private BugService bugService;

    private static final Logger log = LoggerFactory.getLogger(BugController.class);

    @RequestMapping(value = "/api/bug/{bug_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getBugById(@PathVariable Integer bug_id) {
        log.info("### getBugById() START ###");
        ResultBean resultBean = null;
        try {
            resultBean = bugService.getBugById(bug_id);
        } catch (Exception e) {
            resultBean = new ResultBean("404", e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_REQUEST);
        }
        log.info("### getBugById() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/bugs/user/{user_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getBugsByUser(@PathVariable Integer user_id, String name, String date, Integer page, Integer size) {
        log.info("### getTeams() START ###");
        ResultBean resultBean = null;
        resultBean = bugService.getBugsByUser(user_id, name, date, page, size);
        log.info("### getTeams() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/bugs/task/{task_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getBugsByTask(@PathVariable Integer task_id, String name, String date, Integer page, Integer size) {
        log.info("### getTeams() START ###");
        ResultBean resultBean = null;
        resultBean = bugService.getTaskByUser(task_id, name, date, page, size);
        log.info("### getTeams() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/bug", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addBug(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = bugService.addBug(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/bug", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateBug(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = bugService.updateBug(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
