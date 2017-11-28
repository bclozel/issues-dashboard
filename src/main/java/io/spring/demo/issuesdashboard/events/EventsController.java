package io.spring.demo.issuesdashboard.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.spring.demo.issuesdashboard.github.GithubClient;
import io.spring.demo.issuesdashboard.github.RepositoryEvent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/")
	public String dashboard(Model model) {
		List<DashboardEntry> entries = StreamSupport
				.stream(this.repository.findAll().spliterator(), true)
				.map(p -> new DashboardEntry(p, githubClient.fetchEventsList(p.getOrgName(), p.getRepoName())))
				.collect(Collectors.toList());
		model.addAttribute("entries", entries);
		return "dashboard";
	}

}
