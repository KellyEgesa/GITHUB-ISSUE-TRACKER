package com.mamawaestate.githubissuetracker.Network;

import com.mamawaestate.githubissuetracker.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {
    public static Retrofit retrofit = null;

    public static GithubIssueTracker urlRequest(){
        if(retrofit ==null){
            retrofit = new Retrofit.Builder().baseUrl(Constants.GitHubEndPoint)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GithubIssueTracker.class);
    }
}
