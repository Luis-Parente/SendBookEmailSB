package com.devsuperior.SendBookEmailSB.config;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsuperior.SendBookEmailSB.job.SendBookLoanNotificationScheduleJobConfig;

@Configuration
public class QuartzConfig {

	@Bean
	public JobDetail quartzJobDetail() {
		return JobBuilder.newJob(SendBookLoanNotificationScheduleJobConfig.class).storeDurably().build();
	}

	@Bean
	public Trigger jobTrigger() {
		String exp = "0 57 23 * * ?";
		return TriggerBuilder
				.newTrigger()
				.forJob(quartzJobDetail())
				.startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(exp))
				.build();
	}
}
