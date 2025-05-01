package com.company.Personalmgmt.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.company.Personalmgmt.config.EmailJobScheduler;
import com.company.Personalmgmt.model.ScheduleEmailTask;
import com.company.Personalmgmt.repository.ScheduleEmailTaskRepository;

@RestController
public class EmailJobSchedulerController {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmailJobSchedulerController.class);

	@Autowired
	private Scheduler scheduler;
	
	@Autowired
	ScheduleEmailTaskRepository scheduleEmailTaskRepository;
	
	@Value("${cron_email_schedule}")
	private String jobTime;
	
	
	
	//@RequestMapping(value = "/schedulerCheck", method = { RequestMethod.POST, RequestMethod.GET })
	//@Scheduled(cron= "0 0/5 19-23 1-7 * ?")
	@Scheduled(cron= "0 0/5 2-23 1-23 * ?")
	public String scheduleApply() {
		
		String response = "";

		try {
			LocalDateTime localDateTime = LocalDateTime.now();

			LocalDateTime executionTime = localDateTime.plusMinutes(2);

			log.info("ExecutionTime = " + executionTime);

			response = executionTime.toString();
			ZonedDateTime dateTime = ZonedDateTime.of(executionTime, ZoneId.of("Asia/Kolkata"));

			if (dateTime.isBefore(ZonedDateTime.now())) {
				return "Please check the scheule time";
			}

			int year = localDateTime.getYear();

			String month = localDateTime.getMonth().toString();

			ScheduleEmailTask scheduleEmailTask = scheduleEmailTaskRepository
					.findByPurposeAndMonthAndYearAndUserId("Expense Module", month, String.valueOf(year), 1);

			System.out.println(scheduleEmailTask.getIsDone());

			if (scheduleEmailTask.getIsDone().equals("Yes")) {

				return "Already Scheuled! please reset";

			} else {
				scheduleEmailTask.setIsDone("Yes");
				
				scheduleEmailTask.setModifiedDate(new Date());

				scheduleEmailTaskRepository.save(scheduleEmailTask);
			}

			JobDetail jobDetail = buildJobDetail();
			Trigger trigger = buildJobTrigger(jobDetail, dateTime);
			scheduler.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException e) {

			e.printStackTrace();
		}

		return "Email scheduled. Execution time : " + response;
	}
	
	private JobDetail buildJobDetail() {
		JobDataMap jobDataMap = new JobDataMap();

		jobDataMap.put("email", "jayaramp51096@gmail.com");
		jobDataMap.put("subject", "Expense overview");
		jobDataMap.put("body", "Dear Ram,");

		return JobBuilder.newJob(EmailJobScheduler.class).withIdentity(UUID.randomUUID().toString(), "email-jobs")
				.withDescription("Send Email Job").usingJobData(jobDataMap).storeDurably().build();
	}
	
	private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
		return TriggerBuilder.newTrigger().forJob(jobDetail)
				.withIdentity(jobDetail.getKey().getName(), "email-triggers").withDescription("Send Email Trigger")
				.startAt(Date.from(startAt.toInstant()))
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow()).build();
	}

}
