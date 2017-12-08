package com.example.android.bakingrecipes;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.android.bakingrecipes.MainActivityFragment.RESULT_KEY;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by Noga on 12/8/2017.
 */
//not working
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityIntent {

    @Rule
    public IntentsTestRule<MainActivity> intentsTestRule = new IntentsTestRule<>(MainActivity.class);
//    @Before
//    public void setup() {
//        Intents.init();
//    }
    @Before
    public void stubAllExternalIntents() {
        intending(not(isInternal())).respondWith(
                new Instrumentation.ActivityResult(
                        Activity.RESULT_OK, null));
    }


//    @Test
//    public void testOnClickRecipeRecyclerView_openDetailsActivity(){
        //onData(withId(R.id.card_view3))
//                .inAdapterView(withId(R.id.recyclerView2))
//                .atPosition(0)
//                .perform(click());
//        onView(withId(R.id.recyclerView))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
//        intended(hasComponent(DetailsActivity.class.getName()));
//        intended(hasExtraWithKey(RESULT_KEY ));
        @Test
        public void checkIntent_DetailsActivity() {
            onView(ViewMatchers.withId(R.id.recyclerView))
                    .perform(RecyclerViewActions
                            .actionOnItemAtPosition(0,click()));
            intended(hasComponent(
                   DetailsActivity.class.getName()));


    }

//    @After
//    public void tearDown() {
//        Intents.release();
//    }
}
