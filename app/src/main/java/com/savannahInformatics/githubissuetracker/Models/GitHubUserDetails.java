package com.savannahInformatics.githubissuetracker.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Objects;

@Parcel
public class GitHubUserDetails {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("bio")
    @Expose
    private String bio;
    @SerializedName("public_repos")
    @Expose
    private Integer publicRepos;

    /**
     * No args constructor for use in serialization
     */
    public GitHubUserDetails() {
    }

    /**
     * @param bio
     * @param login
     * @param id
     * @param avatarUrl
     * @param name
     * @param publicRepos
     */
    public GitHubUserDetails(String login, Integer id, String avatarUrl, String name, String bio, Integer publicRepos) {
        super();
        this.login = login;
        this.id = id;
        this.avatarUrl = avatarUrl;
        this.name = name;
        this.bio = bio;
        this.publicRepos = publicRepos;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(Integer publicRepos) {
        this.publicRepos = publicRepos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GitHubUserDetails that = (GitHubUserDetails) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(id, that.id) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(name, that.name) &&
                Objects.equals(bio, that.bio) &&
                Objects.equals(publicRepos, that.publicRepos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, id, avatarUrl, name, bio, publicRepos);
    }
}
