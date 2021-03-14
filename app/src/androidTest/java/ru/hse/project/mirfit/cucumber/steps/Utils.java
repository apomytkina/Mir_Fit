package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.action.ViewActions;

import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public final  class Utils {
    public static void closeApp(){
        SystemClock.sleep(1000);
        onView(withId(R.id.prof_lay_setting)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.btn_sign_out)).perform(click());
    }

}
