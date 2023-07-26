package dev.artsman.batch.configurations.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;

@Log
@RequiredArgsConstructor
public class HelloWorldJobConfiguration {
	private final JobBuilderFactory jobFactory;

	@Bean
	public Job helloWorldJob(Step helloWorldStep) {
		return this.jobFactory.get("Job: Hello World")
													.start(helloWorldStep)
													.incrementer(new RunIdIncrementer())
													.build();
	}
}
