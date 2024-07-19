## ✨ GitHub Repo Lister API 
This Spring Boot application provides a REST API to fetch GitHub repositories (which are not forks) and branches for a given user.

### 🛠️ Technologies and Tools Used
- Spring Boot: For building the application framework.
- Spring Web: For building web-based applications with Spring MVC.
- Maven: For project management and dependency management.

### 🚀 Current Functionalities
- ##### Endpoints
  GET/api/github/repositories/{username}   


  Send a GET request with the header `Accept: application/json` to fetch the repositories and branches.
  Lists all non-fork repositories of the given user, along with branch information and last commit SHA.
- ##### Example of response body:
     ```json
     {
      "name": "repository-name",
            "ownerLogin": "owner-login",
            "branches": [
                 {
                   "name": "branch-name",
                   "lastCommitSha": "commit-sha"
                 }
            ]
      }
     ```
##### Prerequisites
- Java 21+
- Maven 4+

##### 👷Author
- LinkedIn: **[Hanna Ratushniak](https://www.linkedin.com/in/hanna-ratushnyak/)**
- Github: **[AnyaRatusnyak](https://github.com/AnyaRatusnyak)**