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
import com.report.service.UserLoginService;
import com.report.service.UserService;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private UserLoginService userLoginService;

    @RequestMapping(value = "/api/leaders", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getLeaders() {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getLeaders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/api/logout", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity logout(HttpServletRequest request) {
        userLoginService.logout(request);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/api/users/add-team", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUsersAddTeam() {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUsersAddTeam();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUsers(String fullname, String date, Integer page, Integer size) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUsers(fullname, date, page, size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/users/team/{team_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getUsersByTeam(@PathVariable Integer team_id) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.getUsersByTeam(team_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addUser(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.add(request, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateUser(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.update(request, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/change-pass", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> changePass(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.changePass(request, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/user/reset", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> reset(HttpServletRequest request, @RequestBody String json) {
        ResultBean resultBean = null;
        try {
            resultBean = userService.reset(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
