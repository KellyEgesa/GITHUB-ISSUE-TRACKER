package com.savannahInformatics.githubissuetracker;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    @Test
    public void constantReturnsGithubEndpoint() {
        String url = "https://api.github.com/";
        assertEquals(url, Constants.GITHUB_ENDPOINT);
    }

    @Test
    public void constantReturnsAccessPoint(){
        assertNotNull(Constants.ACCESS_TOKEN);
    }

}