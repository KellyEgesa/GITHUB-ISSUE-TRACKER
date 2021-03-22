package com.savannahInformatics.githubissuetracker.UserInterface;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.savannahInformatics.githubissuetracker.BuildConfig;
import com.savannahInformatics.githubissuetracker.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class UserNameActivityTest {
    private UserNameActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(UserNameActivity.class);
    }

    @Test
    public void UserNameActivityIsCreated() {
        assertNotNull(activity);
    }

    @Test
    public void validateTextViewContent() {
        TextView headerUserName = activity.findViewById(R.id.userNameHeader);
        TextView enterTextUserName = activity.findViewById(R.id.textViewEnterNameUserName);

        assertNotNull(headerUserName);
        assertNotNull(enterTextUserName);
        assertEquals("GITHUB ISSUE TRACKER", headerUserName.getText());
        assertEquals("Kindly enter a github Username", enterTextUserName.getText());
    }

    @After
    public void tearDown() {
        activity = null;
    }


}
