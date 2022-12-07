package com.example.barterapp;

import android.content.Context;
//import android.support.test.espresso.intent.Intents;
//import android.support.test.espresso.intent.rule.IntentsTestRule;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class EspressoTest {

    @Rule
    public ActivityScenarioRule<MainActivity> myRule = new ActivityScenarioRule<>(MainActivity.class);
    public IntentsTestRule<MainActivity> myIntentRule = new IntentsTestRule<>(MainActivity.class);


    @BeforeClass
    public static void setup() {
        Intents.init();
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.barterapp", appContext.getPackageName());
    }

    @Test
    public void checkIfEmailIsValid() {
        onView(withId(R.id.email)).perform(typeText("abc.233here.com"));
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.emailError)));
    }
    @Test
    public void checkIfFirstNameEmpty() {
        onView(withId(R.id.firstName)).perform(typeText(""));
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.emptyField)));
    }
    @Test
    public void checkIfLastNameEmpty() {
        onView(withId(R.id.lastName)).perform(typeText("abc.233@here.com"));
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.statusLabel)).check(matches(withText(R.string.emptyField)));
    }



    @AfterClass
    public static void tearDown() {
        System.gc();
    }
}