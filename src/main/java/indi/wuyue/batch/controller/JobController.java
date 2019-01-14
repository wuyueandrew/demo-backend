package indi.wuyue.batch.controller;

import indi.wuyue.batch.service.DemoService;
import indi.wuyue.batch.service.JobLauncherService;
import indi.wuyue.batch.util.Result;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RequestMapping(value = "/job")
@RestController
public class JobController {

    @Resource
    private JobExplorer jobExplorer;

    @Resource
    private JobLauncherService jobLauncherService;

    @Resource
    private DemoService demoService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Map test() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<String> jobNames = jobExplorer.getJobNames();
        List<JobInstance> jobInstances = jobExplorer.findJobInstancesByJobName("importUserJob", 0, 10);
        Set<JobExecution> jobExecutions = jobExplorer.findRunningJobExecutions("importUserJob");
//        jobExplorer.getStepExecution()

        map.put("jobNames", jobNames);
        map.put("jobInstances", jobInstances);
        map.put("jobExecutions", jobExecutions);

        return map;
    }

    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public Result run() {
        try {
//            Job job = new SimpleJob();
//            job.setRes
            jobLauncherService.runJob();
            return Result.successResult();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failResult(e.getMessage(), "500");
        }
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) {
        demoService.testExport(response);
    }

    @RequestMapping(value = "/people/list", method = RequestMethod.GET)
    public Result people() {
        return Result.successResult(demoService.testPeopleQuery());
    }

    @RequestMapping(value = "/people/export", method = RequestMethod.GET)
    public void peopleExport(HttpServletResponse response) {
        demoService.testPeopleExport(response);
    }



}
