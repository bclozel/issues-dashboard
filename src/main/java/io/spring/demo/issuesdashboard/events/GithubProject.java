package io.spring.demo.issuesdashboard.events;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class GithubProject implements Serializable {

	@Id
	@GeneratedValue
	private Long id;

	private String orgName;

	@Column(unique = true)
	private String repoName;

	public GithubProject() {
	}

	public GithubProject(String orgName, String repoName) {
		this.orgName = orgName;
		this.repoName = repoName;
	}

	public Long getId() {
		return id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	@Override
	public String toString() {
		return "GithubProject{" +
				"id=" + id +
				", orgName='" + orgName + '\'' +
				", repoName='" + repoName + '\'' +
				'}';
	}
}
