package com.savannahInformatics.githubissuetracker.UserInterface;

import android.os.Looper;
import android.widget.TextView;

import com.savannahInformatics.githubissuetracker.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.LooperMode;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;


@RunWith(RobolectricTestRunner.class)
@LooperMode(LooperMode.Mode.PAUSED)
public class RepositoryActivityTest {
    private RepositoryActivity activity;

    @Before
    public void setUp(){
        activity = Robolectric.setupActivity(RepositoryActivity.class);
    }

    @Test
    public void RepositoryActivityIsCreated(){
        assertNotNull(activity);
    }

    @Test
    public void validateTextView(){
        shadowOf(Looper.getMainLooper()).idle();

        TextView headerRepositories = activity.findViewById(R.id.textViewRepoHeader);
        TextView userName = activity.findViewById(R.id.textViewUserName);
        TextView userBio = activity.findViewById(R.id.textViewBio);
        TextView userReposCount = activity.findViewById(R.id.textViewRepositoriesNumber);
        TextView noRepo = activity.findViewById(R.id.textViewNoRepo);

        assertNotNull(headerRepositories);
        assertNotNull(userBio);
        assertNotNull(userName);
        assertNotNull(userReposCount);
        assertNotNull(noRepo);

        assertEquals("REPOSITORIES", headerRepositories.getText());
        assertEquals("Kelly Egesa", userName.getText());
        assertEquals("Builder and Designer", userBio.getText());
        assertEquals("44", userReposCount.getText());
        assertEquals("There isn't any public repositories yet.", noRepo.getText());
    }

    @After
    public void tearDown() {
        activity = null;
    }

}