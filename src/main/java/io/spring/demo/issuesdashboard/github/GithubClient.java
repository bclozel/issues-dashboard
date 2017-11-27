package io.spring.demo.issuesdashboard.github;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GithubClient {

	private final RestTemplate restTemplate;

	private static final String EVENT_ISSUES_URL = "https://api.github.com/repos/{owner}/{repo}/issues/events";

	public GithubClient(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public ResponseEntity<RepositoryEvent[]> fetchEvents(String orgName, String repoName) {
		return this.restTemplate.getForEntity(EVENT_ISSUES_URL, RepositoryEvent[].class, orgName, repoName);
	}
}
