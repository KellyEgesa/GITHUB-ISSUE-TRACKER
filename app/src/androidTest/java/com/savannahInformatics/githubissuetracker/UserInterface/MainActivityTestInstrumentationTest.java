package com.savannahInformatics.githubissuetracker.UserInterface;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.savannahInformatics.githubissuetracker.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTestInstrumentationTest {

    @Rule
    public ActivityTestRule<UserNameActivity> activityTestRule = new ActivityTestRule<>(UserNameActivity.class);

    @Before
    public void setUp() {
        onView(withId(R.id.editTextGithubUserName)).perform(typeText("KellyEgesa"))
                .perform(closeSoftKeyboard());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.proceedButton)).perform(click());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.recyclerViewRepos)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void mainActivityIsStarted() {
        onView(withId(R.id.textViewToday)).check(matches
                (withText("Today")));
    }

    @Test
    public void recyclerViewIsPopulated() {
        onView(withId(R.id.recyclerViewIssues)).perform(RecyclerViewActions.scrollToPosition(5));
    }

    @Test
    public void backButtonWorks(){
        onView(withId(R.id.imageViewIssueToolBar)).perform(click());
        onView(withId(R.id.textViewRepoHeader)).check(matches(withText("REPOSITORIES")));
    }
}