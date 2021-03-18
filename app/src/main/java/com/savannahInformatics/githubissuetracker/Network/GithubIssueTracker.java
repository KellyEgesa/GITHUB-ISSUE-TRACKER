package com.savannahInformatics.githubissuetracker.Network;

import com.savannahInformatics.githubissuetracker.Models.GitHubUserDetails;
import com.savannahInformatics.githubissuetracker.Models.GitHubUserRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubIssueTracker {
    @GET("users/{username}?access_token=6beb52dc93deb3df43fb52290ae9b3f8fc9f4a4d")
    Call<GitHubUserDetails> checkUser(@Path("username") String username);

    @GET("users/{GithubUserName}/repos?access_token=6beb52dc93deb3df43fb52290ae9b3f8fc9f4a4d")
    Call<List <GitHubUserRepo>> getUserRepos(@Path("GithubUserName") String GithubUserName);

}
