package io.spring.demo.issuesdashboard.events;

import org.springframework.data.repository.CrudRepository;

public interface GithubProjectRepository extends CrudRepository<GithubProject, Long> {

	GithubProject findByRepoName(String repoName);
}
