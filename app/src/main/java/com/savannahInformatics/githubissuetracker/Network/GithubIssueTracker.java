package com.savannahInformatics.githubissuetracker.Network;

import com.savannahInformatics.githubissuetracker.Models.GitHubUserDetails;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubIssueTracker {
    @GET("users/{username}")
    Call<GitHubUserDetails> checkUser(@Path("username") String username);

    @GET("users/{username}/repos")
    Call<List <GitHubUserRepo>> getUserRepos(@Path("username") String username);

}
