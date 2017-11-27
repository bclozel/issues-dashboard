CREATE TABLE GITHUB_PROJECT (
  id        IDENTITY NOT NULL PRIMARY KEY,
  org_name  VARCHAR(50),
  repo_name VARCHAR(50),
);

CREATE INDEX idx_repo_name
  ON GITHUB_PROJECT (repo_name);