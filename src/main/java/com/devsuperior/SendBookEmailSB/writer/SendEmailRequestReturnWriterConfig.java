package com.devsuperior.SendBookEmailSB.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsuperior.SendBookEmailSB.domain.UserBookLoan;

@Configuration
public class SendEmailRequestReturnWriterConfig {

	@Bean
	public ItemWriter<UserBookLoan> sendEmailRequestReturnWriter() {
		return items -> items.forEach(System.out::println);
	}

}
