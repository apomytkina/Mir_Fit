package ru.hse.project.mirfit.cucumber.steps;

import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public final  class Utils {
    public static void closeApp(){
        onView(withId(R.id.prof_lay_setting)).perform(click());
        onView(withId(R.id.btn_sign_out)).perform(click());
    }

}
