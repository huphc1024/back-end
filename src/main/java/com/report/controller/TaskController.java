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
import com.report.service.TaskService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@Controller
public class TaskController {

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "/api/task/{task_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTaskById(@PathVariable Integer task_id) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.getTaskById(task_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/tasks/team/{team_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTasksByTeam(@PathVariable Integer team_id, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = taskService.getTasksByTeam(team_id, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/tasks/user/{user_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getTasksByUser(@PathVariable Integer user_id, String name, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        resultBean = taskService.getTasksByUser(user_id, name, date, page, size);
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/task", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> add(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = taskService.add(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/task", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
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
