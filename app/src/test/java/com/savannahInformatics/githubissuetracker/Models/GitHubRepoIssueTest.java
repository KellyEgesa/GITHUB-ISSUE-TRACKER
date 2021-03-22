package com.savannahInformatics.githubissuetracker.Models;

import org.junit.Test;

import static org.junit.Assert.*;

public class GitHubRepoIssueTest {

    private GitHubUserDetails newUser() {
        return new GitHubUserDetails("kellyegesa", 1234, "https://avatars.githubusercontent.com/u/40017946?v=4", "Kelly Egesa", "Builder and Designer", 44);
    }

    private GitHubUserDetails newUser2() {
        return new GitHubUserDetails("jamesmaina", 5678, "https://avatars.githubusercontent.com/u/40017456?v=5", "James Maina", "Inventor", 10);
    }


    private GitHubRepoIssue newIssue() {
        return new GitHubRepoIssue(1234, 5678, "Compilation Error", newUser(), "Open", 12, "2018-06-07T06:24:09Z");
    }

    @Test
    public void GitHubRepoIssueInstantiatesCorrectly_True() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertTrue(gitHubRepoIssue instanceof GitHubRepoIssue);
    }

    @Test
    public void GitHubRepoIssueInstantiatesCorrectlyWithNoMethod_True() {
        GitHubRepoIssue gitHubRepoIssue = new GitHubRepoIssue();
        assertTrue(gitHubRepoIssue instanceof GitHubRepoIssue);
    }

    @Test
    public void getIdReturnsCorrectId_1234() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals(1234, (int) gitHubRepoIssue.getId());
    }

    @Test
    public void setIdSetsCorrectId_4321() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setId(4321);
        assertNotEquals(1234, (int) gitHubRepoIssue.getId());
        assertEquals(4321, (int) gitHubRepoIssue.getId());
    }

    @Test
    public void getNumberReturnsCorrectName_5678() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals(5678, (int) gitHubRepoIssue.getNumber());
    }

    @Test
    public void setNumberSetsCorrectId_8765() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setId(8765);
        assertNotEquals(5678, (int) gitHubRepoIssue.getId());
        assertEquals(8765, (int) gitHubRepoIssue.getId());
    }

    @Test
    public void getTitleReturnsCorrectTitle_CompilationError() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals("Compilation Error", gitHubRepoIssue.getTitle());
    }

    @Test
    public void setTitleSetsTitleCorrectly_BugFound() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setTitle("Bug found");

        assertNotEquals("Compilation Error", gitHubRepoIssue.getTitle());
        assertEquals("Bug found", gitHubRepoIssue.getTitle());
    }

    @Test
    public void getUserReturnsCorrectUser() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        GitHubUserDetails gitHubUserDetails = newUser();

        assertEquals(gitHubUserDetails.getLogin(), gitHubRepoIssue.getUser().getLogin());
        assertEquals(gitHubRepoIssue.getUser(), gitHubUserDetails);
    }

    @Test
    public void setUserSetsUserCorrectly() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        GitHubUserDetails gitHubUserDetails = newUser();
        GitHubUserDetails gitHubUserDetails2 = newUser2();

        gitHubRepoIssue.setUser(gitHubUserDetails2);

        assertNotEquals(gitHubRepoIssue.getUser(), gitHubUserDetails);
        assertEquals(gitHubUserDetails2, gitHubRepoIssue.getUser());
    }

    @Test
    public void getStateReturnsCorrectState_Open() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals("Open", gitHubRepoIssue.getState());
    }

    @Test
    public void setStateSetsStateCorrectly_Closed() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setState("Closed");

        assertNotEquals("Open", gitHubRepoIssue.getState());
        assertEquals("Closed", gitHubRepoIssue.getState());
    }

    @Test
    public void getCommentsReturnsCorrectComment_12() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals(12, (int) gitHubRepoIssue.getComments());
    }

    @Test
    public void setComments() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setComments(29);

        assertNotEquals(12, (int) gitHubRepoIssue.getComments());
        assertEquals(29, (int) gitHubRepoIssue.getComments());

    }

    @Test
    public void getCreatedAtReturnsCorrectly() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        assertEquals("2018-06-07T06:24:09Z",  gitHubRepoIssue.getCreatedAt());
    }

    @Test
    public void setCreatedAt() {
        GitHubRepoIssue gitHubRepoIssue = newIssue();
        gitHubRepoIssue.setCreatedAt("2021-03-21T19:40:48Z");

        assertNotEquals("2018-06-07T06:24:09Z",  gitHubRepoIssue.getCreatedAt());
        assertEquals("2021-03-21T19:40:48Z",  gitHubRepoIssue.getCreatedAt());
    }
}