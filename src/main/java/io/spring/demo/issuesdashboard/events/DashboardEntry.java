package io.spring.demo.issuesdashboard.events;

import java.util.List;

import io.spring.demo.issuesdashboard.github.RepositoryEvent;

public class DashboardEntry {

	private final GithubProject project;

	private final List<RepositoryEvent> events;

	public DashboardEntry(GithubProject project, List<RepositoryEvent> events) {
		this.project = project;
		this.events = events;
	}

	public GithubProject getProject() {
		return project;
	}

	public List<RepositoryEvent> getEvents() {
		return events;
	}
}