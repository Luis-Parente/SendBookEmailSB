package com.devsuperior.SendBookEmailSB.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendBookLoanNotificationJobConfig {

	private static Logger logger = LoggerFactory.getLogger(SendBookLoanNotificationJobConfig.class);

    @Bean
    Job sendBookLoanNotificationJob(JobRepository jobRepository, Step sendEmailUserStep) {
		logger.info("Start job execution....");
		return new JobBuilder("sendBookLoanNotificationJob", jobRepository).start(sendEmailUserStep)
				.incrementer(new RunIdIncrementer()).build();
	}
}
