package com.example.android.bakingrecipes;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Noga on 12/8/2017.
 */
//not working
@RunWith(AndroidJUnit4.class)

public class StepTest {
    @Rule
    public ActivityTestRule<DetailsActivity> detailsActivityTest = new ActivityTestRule<>(DetailsActivity.class);

    @Test
    public void testClickonRecipeRecyclerview_OpenDetailsRecipeStepActivity() {
        //not working
//        onData(withId(R.id.card_view3))
//                .inAdapterView(withId(R.id.recyclerView2))
//                .atPosition(0)
//                .perform(click());
        //not working
//        onView(withId(R.id.recyclerView2))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        //not working
//        ViewInteraction recyclerView = onView(
//                allOf(withId(R.id.recyclerView2),
//                        withParent(withId(R.id.container)),
//                        isDisplayed()));
//        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }
}
