package io.spring.demo.issuesdashboard.events;

import io.spring.demo.issuesdashboard.github.GithubClient;
import io.spring.demo.issuesdashboard.github.RepositoryEvent;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EventsController {

	private final GithubClient githubClient;

	private final GithubProjectRepository repository;

	public EventsController(GithubClient githubClient, GithubProjectRepository repository) {
		this.githubClient = githubClient;
		this.repository = repository;
	}

	@GetMapping("/events/{projectName}")
	@ResponseBody
	public RepositoryEvent[] fetchEvents(@PathVariable String projectName) {

		GithubProject project = this.repository.findByRepoName(projectName);
		return this.githubClient.fetchEvents(project.getOrgName(), project.getRepoName()).getBody();
	}

}
