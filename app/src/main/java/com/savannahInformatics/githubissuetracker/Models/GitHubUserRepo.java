
package com.savannahInformatics.githubissuetracker.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class GitHubUserRepo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("owner")
    @Expose
    private GitHubUserDetails owner;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("language")
    @Expose
    private String language;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GitHubUserRepo() {
    }

    /**
     * 

     * @param language
     * @param _private
     * @param id
     * @param updatedAt
     * @param name
     * @param description
     * @param owner
     */
    public GitHubUserRepo(Integer id, String name, Boolean _private, GitHubUserDetails owner, String description, String updatedAt, String language) {
        super();
        this.id = id;
        this.name = name;
        this._private = _private;
        this.owner = owner;
        this.description = description;
        this.updatedAt = updatedAt;
        this.language = language;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public GitHubUserDetails getOwner() {
        return owner;
    }

    public void setOwner(GitHubUserDetails owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


}
