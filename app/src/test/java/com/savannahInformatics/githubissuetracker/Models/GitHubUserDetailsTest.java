package com.savannahInformatics.githubissuetracker.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GitHubUserDetailsTest {

    private GitHubUserDetails newUser() {
        return new GitHubUserDetails("kellyegesa", 1234, "https://avatars.githubusercontent.com/u/40017946?v=4", "Kelly Egesa", "Builder and Designer", 44);
    }

    @Test
    public void GitHubUserDetailsInstantiatesCorrectly_True() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertTrue(gitHubUserDetailsTest instanceof GitHubUserDetails);
    }

    @Test
    public void getLoginReturnsCorrectLogin_kellyegesa() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals("kellyegesa", gitHubUserDetailsTest.getLogin());
    }

    @Test
    public void setLoginSetsCorrectly_JamesMain() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setLogin("jamesmain");
        assertNotEquals("kellyegesa", gitHubUserDetailsTest.getLogin());
        assertEquals("jamesmain", gitHubUserDetailsTest.getLogin());
    }

    @Test
    public void getIdReturnsCorrectId_1234() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals(1234, (int) gitHubUserDetailsTest.getId());
    }

    @Test
    public void setIdSetsCorrectly_4321() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setId(4321);
        assertNotEquals(1234, (int) gitHubUserDetailsTest.getId());
        assertEquals(4321, (int) gitHubUserDetailsTest.getId());
    }

    @Test
    public void getAvatarUrlReturnsCorrectAvatar() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals("https://avatars.githubusercontent.com/u/40017946?v=4", gitHubUserDetailsTest.getAvatarUrl());
    }

    @Test
    public void setAvatarUrlSetsCorrectly() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setAvatarUrl("https://avatars.githubusercontent.com/u/40017946?v=3");
        assertNotEquals("https://avatars.githubusercontent.com/u/40017946?v=4", gitHubUserDetailsTest.getAvatarUrl());
        assertEquals("https://avatars.githubusercontent.com/u/40017946?v=3", gitHubUserDetailsTest.getAvatarUrl());
    }

    @Test
    public void getNameReturnsCorrectName_KellyEgesa() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals("Kelly Egesa", gitHubUserDetailsTest.getName());
    }

    @Test
    public void setNameSetsCorrectly_JamesMain() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setName("James Main");
        assertNotEquals("Kelly Egesa", gitHubUserDetailsTest.getName());
        assertEquals("James Main", gitHubUserDetailsTest.getName());
    }

    @Test
    public void getBioReturnsCorrectBio_BuilderAndDesigner() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals("Builder and Designer", gitHubUserDetailsTest.getBio());
    }

    @Test
    public void setBioSetsCorrectly_BuildTheFuture() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setBio("Build the future");
        assertNotEquals("Builder and Designer", gitHubUserDetailsTest.getBio());
        assertEquals("Build the future", gitHubUserDetailsTest.getBio());
    }

    @Test
    public void getPublicReposReturnsCorrectRepo_44() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        assertEquals(44, (int) gitHubUserDetailsTest.getPublicRepos());
    }

    @Test
    public void setPublicReposSetsCorrectly_10() {
        GitHubUserDetails gitHubUserDetailsTest = newUser();
        gitHubUserDetailsTest.setPublicRepos(10);
        assertNotEquals(44, (int) gitHubUserDetailsTest.getPublicRepos());
        assertEquals(10, (int) gitHubUserDetailsTest.getPublicRepos());
    }
}