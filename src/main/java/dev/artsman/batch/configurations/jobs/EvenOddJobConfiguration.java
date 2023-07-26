package dev.artsman.batch.configurations.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@Log
@RequiredArgsConstructor
public class EvenOddJobConfiguration {
	private final JobBuilderFactory jobFactory;

	@Bean
	public Job evenOddJob(Step evenOddStep) {
		return this.jobFactory.get("Job: Even-Odd")
													.start(evenOddStep)
													.build();
	}
}
