CREATE TABLE github_project (
  id        IDENTITY NOT NULL PRIMARY KEY,
  org_name  VARCHAR(50),
  repo_name VARCHAR(50)
);

CREATE INDEX idx_repo_name
  ON github_project (repo_name);