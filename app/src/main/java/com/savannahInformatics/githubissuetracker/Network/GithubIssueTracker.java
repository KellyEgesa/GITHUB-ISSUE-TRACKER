package com.savannahInformatics.githubissuetracker.Network;

import com.savannahInformatics.githubissuetracker.Constants;
import com.savannahInformatics.githubissuetracker.Models.GitHubRepoIssue;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserDetails;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubIssueTracker {
    @GET("users/{username}" + Constants.ACCESS_TOKEN)
    Call<GitHubUserDetails> checkUser(@Path("username") String username);

    @GET("users/{GithubUserName}/repos" + Constants.ACCESS_TOKEN)
    Call<List<GitHubUserRepo>> getUserRepos(@Path("GithubUserName") String GithubUserName);

    @GET("repos/{GithubUserName}/{repoName}/issues")
    Call<List<GitHubRepoIssue>> getRepoIssues(@Path("GithubUserName") String GithubUserName, @Path("repoName") String repoName);
}
