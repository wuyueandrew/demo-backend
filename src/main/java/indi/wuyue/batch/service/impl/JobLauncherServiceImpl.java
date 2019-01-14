package indi.wuyue.batch.service.impl;

import indi.wuyue.batch.service.JobLauncherService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class JobLauncherServiceImpl implements JobLauncherService {

    @Resource
    private JobLauncher jobLauncher;

    @Resource
    private Job importUserJob;

    @Override
    public void runJob() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String curDateStr = sdf.format(new Date());
        Date curDate = sdf.parse(curDateStr);
        jobLauncher.run(importUserJob, new JobParametersBuilder()
                .addDate("run.date", curDate, true)
                .toJobParameters());
    }
}
