package dev.artsman.batch.configurations;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
@Log
@RequiredArgsConstructor
public class EvenOddBatchConfiguration {
	private final JobBuilderFactory jobFactory;
	private final StepBuilderFactory stepFactory;

	@Bean
	public Job evenOddJob() {
		return this.jobFactory.get("Job: Even-Odd")
													.start(this.evenOddStep())
													.build();
	}

	private Step evenOddStep() {
		return this.stepFactory.get("Step: Even-Odd")
													 .<Integer, String>chunk(1)
													 .reader(this.countToTenReader())
													 .processor(this.evenOddProcessor())
													 .writer(this.evenOddWriter())
													 .build();
	}

	private ItemReader<Integer> countToTenReader() {
		List<Integer> oneToTen = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		return new IteratorItemReader<>(oneToTen);
	}

	private ItemProcessor<? super Integer, String> evenOddProcessor() {
		IntPredicate isEven = number -> number % 2 == 0;
		return (ItemProcessor<Integer, String>) item -> isEven.test(item) ? itemIs(item, "Even") : itemIs(item, "Odd");
	}

	private String itemIs(Integer item, String evenOrOdd) {
		String template = "Item %d is %s";
		return String.format(template, item, evenOrOdd);
	}

	private ItemWriter<? super String> evenOddWriter() {
		return items -> items.forEach(log::info);
	}
}
