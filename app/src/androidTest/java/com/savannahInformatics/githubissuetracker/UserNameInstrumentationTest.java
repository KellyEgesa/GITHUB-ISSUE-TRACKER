package com.savannahInformatics.githubissuetracker;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.savannahInformatics.githubissuetracker.UserInterface.UserNameActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserNameInstrumentationTest {

    @Rule
    public ActivityTestRule<UserNameActivity> activityTestRule = new ActivityTestRule<>(UserNameActivity.class);


    @Test
    public void validateEditText(){
        onView(withId(R.id.editTextGithubUserName)).perform(typeText("KellyEgesa"))
                .check(matches(withText("KellyEgesa")));
    }

    @Test
    public void validateButton(){
        onView(withId(R.id.editTextGithubUserName)).perform(click());
    }

}
