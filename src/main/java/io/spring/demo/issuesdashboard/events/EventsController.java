package io.spring.demo.issuesdashboard.events;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.spring.demo.issuesdashboard.github.GithubClient;
import io.spring.demo.issuesdashboard.github.RepositoryEvent;

import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<RepositoryEvent[]> fetchEvents(@PathVariable String projectName) {
		GithubProject project = this.repository.findByRepoName(projectName);
		if (project == null) {
			return ResponseEntity.notFound().build();
		}
		ResponseEntity<RepositoryEvent[]> response = this.githubClient
				.fetchEvents(project.getOrgName(), project.getRepoName());
		return ResponseEntity.ok()
				.eTag(response.getHeaders().getETag())
				.body(response.getBody());
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

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("projects", repository.findAll());
		return "admin";
	}

}
