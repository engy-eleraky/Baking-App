package com.example.android.bakingrecipes;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Noga on 12/7/2017.
 */

//works
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> mainActivityTest = new ActivityTestRule<>(MainActivity.class) ;
    private IdlingResource idlingResource ;

    @Before
    public void registerIdlingResource() {
        idlingResource = mainActivityTest.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(idlingResource);
    }

    @Test
    public void testClickonRecipeRecyclerview_OpenDetailsActivity(){
//  it works
//      onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
//it works
//        onView(withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        //not work
//        onView(withId(R.id.card_view3))
//                .check(matches(isDisplayed())) ;
        //not work
//        onData(withId(R.id.card_view3))
//                .inAdapterView(withId(R.id.recyclerView2))
//                .atPosition(0)
//                .perform(click());
        //it works

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.recyclerView),
                        withParent(withId(R.id.fragment)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
    }



    @After
    public void unregisterIdlingResource() {
        if(idlingResource != null )
            Espresso.unregisterIdlingResources(idlingResource);
    }
}
