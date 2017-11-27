package io.spring.demo.issuesdashboard.github;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@RestClientTest(GithubClient.class)
public class GithubClientTests {

	@Autowired
	private MockRestServiceServer mockRestServiceServer;

	@Autowired
	private GithubClient githubClient;

	@Test
	public void fetchEvents() {
		this.mockRestServiceServer
				.expect(requestTo("https://api.github.com/repos/spring-projects/spring-boot/issues/events"))
				.andRespond(withSuccess(new ClassPathResource("events.json", getClass()), MediaType.APPLICATION_JSON));

		ResponseEntity<RepositoryEvent[]> responseEntity = this.githubClient.fetchEvents("spring-projects", "spring-boot");
		List<RepositoryEvent> repositoryEvents = Arrays.asList(responseEntity.getBody());

		assertThat(repositoryEvents).hasSize(1);
		assertThat(repositoryEvents.get(0).getIssue().getTitle()).startsWith("Mine bitcoins");
		assertThat(repositoryEvents.get(0).getActor().getLogin()).isEqualTo("bclozel");
	}

}