package com.example.android.bakingrecipes;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
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
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Noga on 12/7/2017.
 */
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

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));

        onView(withId(R.id.card_view3))
                .check(matches(isDisplayed())) ;


    }



    @After
    public void unregisterIdlingResource() {
        if(idlingResource != null )
            Espresso.unregisterIdlingResources(idlingResource);
    }
}
