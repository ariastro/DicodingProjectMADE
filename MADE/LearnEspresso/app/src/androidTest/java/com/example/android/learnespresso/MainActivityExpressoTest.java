package com.example.android.learnespresso;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityExpressoTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void ensureTextChangeWork(){
        onView(withId(R.id.edt_text)).perform(typeText("HELLO"), closeSoftKeyboard());
        onView(withId(R.id.btn_change_text)).perform(click());
        onView(withId(R.id.tv_printed_text)).check(matches(withText("Lalala")));
    }

    @Test
    public void ensureMoveActivityWithData(){
        onView(withId(R.id.edt_text)).perform(typeText("Aku anak sehat"),closeSoftKeyboard());
        onView(withId(R.id.btn_switch_activity)).perform(click());
        onView(withId(R.id.tv_result_view)).check(matches(withText("Aku anak sehat")));
    }
}
