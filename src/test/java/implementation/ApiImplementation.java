package implementation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

public class ApiImplementation {

    Response response;

    public ApiImplementation(String baseURL,String user,String repo) {
        if(baseURL == null && user == null && repo == null) {
            throw new RuntimeException("base uri,user and repo should not be null");
        }
        response = RestAssured.given()
                .log().all().baseUri(baseURL)
                .basePath(user)
                .contentType(ContentType.JSON)
                .get(repo)
                .then()
                .extract()
                .response();
    }

    public ApiImplementation(String baseURL,String user) {
        if(baseURL == null && user == null ) {
            throw new RuntimeException("base uri,user should not be null");
        }
        response = RestAssured.given()
                .log().all().baseUri(baseURL)
                .contentType(ContentType.JSON)
                .get(user)
                .then()
                .extract()
                .response();
    }

    public String getUserId() {
        String userID = response.jsonPath().getString("login");
        return userID;
    }

    public String getUserName() {
        String name = response.jsonPath().getString("name");
        return name;
    }

    public String getCreatedAt() {
        String createdAt = response.jsonPath().getString("created_at");
        return createdAt;
    }

    public int getStatusCodeFromResponse() {
        return this.response.getStatusCode();
    }

    public List<Object> getRepoNames() {
        List<Object> names = response.getBody().jsonPath().getList("name");
        return names;
    }

    public String getStarCount(String repoName) {
        return response.getBody().jsonPath().getString("findAll{it.name=='"+repoName+"'}.stargazers_count[0]");

    }

    public String getReleaseCount(String repoName) {
        String release = response.getBody().jsonPath().getString("findAll{it.name=='" + repoName + "'}.releases[0]");
        return release == null ? "0" : release;
    }


}
