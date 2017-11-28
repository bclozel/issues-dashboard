package io.spring.demo.issuesdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(GithubProperties.class)
@EnableCaching
public class IssuesDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(IssuesDashboardApplication.class, args);
	}
}
