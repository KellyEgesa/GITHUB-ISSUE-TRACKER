package com.mamawaestate.githubissuetracker.Network;

import retrofit2.Retrofit;

public class GitHubClient {
    public static Retrofit retrofit = null;

    public static GithubIssueTracker urlRequest(){
        if(retrofit ==null){
            retrofit = new Retrofit.Builder().baseUrl(Con)
        }
    }
}
