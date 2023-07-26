package dev.artsman.batch.configurations.tasklets;

import lombok.extern.java.Log;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
public class HelloWorldTaskletConfiguration {
	@Bean
	public Tasklet helloWorldTasklet() {
		return (contribution, chunkContext) -> {
			log.info("Tasklet: Hello World");
			return RepeatStatus.FINISHED;
		};
	}
}
