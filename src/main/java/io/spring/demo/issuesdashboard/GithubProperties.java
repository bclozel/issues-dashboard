package io.spring.demo.issuesdashboard;

import javax.validation.constraints.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("github")
@Validated
public class GithubProperties {

	/**
	 * Github private access token "user:token"
	 */
	@Pattern(regexp = "\\w+:\\w+")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
