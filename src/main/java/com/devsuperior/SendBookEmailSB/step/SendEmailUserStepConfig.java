package com.devsuperior.SendBookEmailSB.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.devsuperior.SendBookEmailSB.domain.UserBookLoan;
import com.sendgrid.helpers.mail.Mail;

@Configuration
public class SendEmailUserStepConfig {

	@Autowired
	@Qualifier("transactionManagerApp")
	private PlatformTransactionManager transactionManager;

	@Bean
	public Step sendEmailUserStep(ItemReader<UserBookLoan> readUsersWithLoanCloseToReturnReader,
			ItemWriter<Mail> sendEmailRequestReturnWriter,
			ItemProcessor<UserBookLoan, Mail> processLoanNotificationEmailProcessor, JobRepository jobRepository) {
		return new StepBuilder("sendEmailUserStep", jobRepository).<UserBookLoan, Mail>chunk(1, transactionManager)
				.reader(readUsersWithLoanCloseToReturnReader).writer(sendEmailRequestReturnWriter)
				.processor(processLoanNotificationEmailProcessor).build();
	}
}
