package com.mercadolibre.solarsystem;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.mercadolibre.solarsystem.repository")
public class SistemaSolarApplication implements CommandLineRunner{

	@Autowired
    JobLauncher jobLauncher;
     
    @Autowired
    Job job;
    
	public static void main(String[] args) {
		SpringApplication.run(SistemaSolarApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		JobParameters params = new JobParametersBuilder()
//                .addString("JobID", String.valueOf(System.currentTimeMillis()))
//                .toJobParameters();
//		jobLauncher.run(job, params);
		
	}

}
