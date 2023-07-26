package dev.artsman.batch.configurations.steps;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log
@RequiredArgsConstructor
public class HelloWorldStepConfiguration {
	private final StepBuilderFactory factory;

	@Bean
	public Step helloWorldStep(Tasklet helloWorldTasklet) {
		return this.factory.get("Step: Hello World")
											 .tasklet(helloWorldTasklet)
											 .build();
	}
}
