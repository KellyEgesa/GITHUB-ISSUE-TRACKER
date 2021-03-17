package com.savannahInformatics.githubissuetracker.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubIssueTracker {
    @GET("users/{username}")
    Call checkUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call getUserRepos(@Path("username") String username);

}
