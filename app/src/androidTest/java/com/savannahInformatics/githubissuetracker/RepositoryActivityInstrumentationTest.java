package com.savannahInformatics.githubissuetracker;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.savannahInformatics.githubissuetracker.UserInterface.RepositoryActivity;
import com.savannahInformatics.githubissuetracker.UserInterface.UserNameActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RepositoryActivityInstrumentationTest {
    @Rule
    public ActivityTestRule<UserNameActivity> activityTestRule = new ActivityTestRule<>(UserNameActivity.class);

    @Before
    public void moveToTheNextActivity() {
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
    }

    @Test
    public void recyclerViewIsPopulated() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recyclerViewRepos);
        int item = recyclerView.getAdapter().getItemCount();
        onView(withId(R.id.recyclerViewRepos)).perform(RecyclerViewActions.scrollToPosition(item - 1));
    }

    @Test
    public void recyclerViewItemIsClickedNextActivity() {
        RecyclerView recyclerView = activityTestRule.getActivity().findViewById(R.id.recyclerViewRepos);
        onView(withId(R.id.recyclerViewRepos)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.textViewToday)).check(matches
                (withText("Today")));
    }


}
