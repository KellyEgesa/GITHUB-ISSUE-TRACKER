package com.savannahInformatics.githubissuetracker.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GitHubUserRepoTest {

    private GitHubUserDetails newUser() {
        return new GitHubUserDetails("kellyegesa", 1234, "https://avatars.githubusercontent.com/u/40017946?v=4", "Kelly Egesa", "Builder and Designer", 44);
    }

    private GitHubUserDetails newUser2() {
        return new GitHubUserDetails("jamesmaina", 5678, "https://avatars.githubusercontent.com/u/40017456?v=5", "James Maina", "Inventor", 10);
    }


    private GitHubUserRepo newRepo() {
        return new GitHubUserRepo(5678, "Github Issue Tracker", true, newUser(), "Checks for bugs in github", "2021-03-21T19:40:48Z", "Java");
    }

    @Test
    public void GitHubUserRepoInstantiatesCorrectly_True() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertTrue(gitHubUserRepo instanceof GitHubUserRepo);
    }

    @Test
    public void getIdReturnsCorrectId_1234() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertEquals(5678, (int) gitHubUserRepo.getId());
    }

    @Test
    public void setIdSetsCorrectId_4321() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        gitHubUserRepo.setId(4321);
        assertNotEquals(1234, (int) gitHubUserRepo.getId());
        assertEquals(4321, (int) gitHubUserRepo.getId());
    }

    @Test
    public void getNameReturnsCorrectName_GithubIssueTracker() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertEquals("Github Issue Tracker", gitHubUserRepo.getName());
    }

    @Test
    public void setNameSetsCorrectName_HelloWorld() {
        GitHubUserRepo gitHubUserRepo = newRepo();

        gitHubUserRepo.setName("Hello World");
        assertNotEquals("GithubIssueTracker", gitHubUserRepo.getName());
        assertEquals("Hello World", gitHubUserRepo.getName());
    }

    @Test
    public void getPrivateReturnsCorrectly_True() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertTrue(gitHubUserRepo.getPrivate());
    }

    @Test
    public void setPrivateSetsCorrectly_False() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        gitHubUserRepo.setPrivate(false);
        assertFalse(gitHubUserRepo.getPrivate());
    }

    @Test
    public void getOwnerReturnsCorrectOwner() {
        GitHubUserDetails gitHubUserDetails = newUser();
        GitHubUserRepo gitHubUserRepo = newRepo();

        assertEquals(gitHubUserDetails, gitHubUserRepo.getOwner());
    }

    @Test
    public void setOwnerSetsCorrectly() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        GitHubUserDetails gitHubUserDetails = newUser();
        GitHubUserDetails gitHubUserDetails2 = newUser2();

        gitHubUserRepo.setOwner(gitHubUserDetails2);
        assertNotEquals(gitHubUserDetails, gitHubUserRepo.getOwner());
        assertEquals(gitHubUserDetails2, gitHubUserRepo.getOwner());
    }

    @Test
    public void getDescriptionReturnsCorrectDescription() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertEquals("Checks for bugs in github", gitHubUserRepo.getDescription());
    }

    @Test
    public void setDescriptionSetsDescription() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        gitHubUserRepo.setDescription("My first code");

        assertNotEquals("Checks for bugs in github", gitHubUserRepo.getDescription());
        assertEquals("My first code", gitHubUserRepo.getDescription());
    }

    @Test
    public void getUpdatedAtReturnsCorrectly() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertEquals("2021-03-21T19:40:48Z", gitHubUserRepo.getUpdatedAt());
    }

    @Test
    public void setUpdatedAtSetsCorrectly() {
        GitHubUserRepo gitHubUserRepo = newRepo();

        gitHubUserRepo.setUpdatedAt("2018-06-07T06:24:09Z");
        assertNotEquals("2021-03-21T19:40:48Z", gitHubUserRepo.getUpdatedAt());
        assertEquals("2018-06-07T06:24:09Z", gitHubUserRepo.getUpdatedAt());
    }

    @Test
    public void getLanguageReturnsCorrectLanguage_Java() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        assertEquals("Java", gitHubUserRepo.getLanguage());
    }

    @Test
    public void setLanguageSetsLanguageCorrectly_Kotlin() {
        GitHubUserRepo gitHubUserRepo = newRepo();
        gitHubUserRepo.setLanguage("Kotlin");

        assertNotEquals("Java", gitHubUserRepo.getLanguage());
        assertEquals("Kotlin", gitHubUserRepo.getLanguage());
    }
}