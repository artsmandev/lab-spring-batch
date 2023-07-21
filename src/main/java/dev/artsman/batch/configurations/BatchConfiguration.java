package dev.artsman.batch.configurations;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@Log
@RequiredArgsConstructor
public class BatchConfiguration {
	private final JobBuilderFactory jobFactory;
	private final StepBuilderFactory stepFactory;

	@Bean
	public Job helloWorldJob() {
		return this.jobFactory.get("Job: Hello World")
													.start(this.helloWorldStep())
													.incrementer(new RunIdIncrementer())
													.build();
	}

	private Step helloWorldStep() {
		return this.stepFactory.get("Step: Hello World")
													 .tasklet(this.helloWorldTasklet())
													 .build();
	}

	private Tasklet helloWorldTasklet() {
		return (contribution, chunkContext) -> {
			log.info("Tasklet: Hello World");
			return RepeatStatus.FINISHED;
		};
	}
}
