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
import com.report.service.ReportService;

@Controller
public class ReportController {

    @Resource
    private ReportService reportService;

    private static final Logger log = LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/api/report/{report_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getReportById(@PathVariable Integer report_id) {
        log.info("### getReportById() START ###");
        ResultBean resultBean = null;
        resultBean = reportService.getReportById(report_id);
        log.info("### getReportById() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/reports/task/{task_id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> getReportsByTask(@PathVariable Integer task_id) {
        log.info("### getReportsByTask() START ###");
        ResultBean resultBean = null;
        resultBean = reportService.getReportsByTask(task_id);
        log.info("### getReportsByTask() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/report", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> addReport(HttpServletRequest request, @RequestBody String json) {
        log.info("### addReport() START ###");
        ResultBean resultBean = null;
        try {
            resultBean = reportService.addReport(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        log.info("### addReport() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/report", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ResultBean> updateReport(HttpServletRequest request, @RequestBody String json) {
        log.info("### updateReport() START ###");
        ResultBean resultBean = null;
        try {
            resultBean = reportService.updateReport(request, json);
        } catch (Exception e) {
            return new ResponseEntity<ResultBean>(HttpStatus.BAD_REQUEST);
        }
        log.info("### updateReport() END ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }
}
