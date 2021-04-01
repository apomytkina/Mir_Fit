package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;

import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public final  class Utils {
    public static void closeApp(){
        SystemClock.sleep(3000);
        onView(withId(R.id.prof_lay_setting)).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_sign_out)).perform(click());
    }
    public static void closeCurrentCheck(int numberSteps){
        for (int i = 0; i < numberSteps; i++) {
            Espresso.pressBackUnconditionally();
        }

    }
    public static void deleteUserAndCloseApp(){
        SystemClock.sleep(3000);
        onView(withId(R.id.prof_lay_setting)).perform(click());
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_delete_user)).perform(click());
        onView(withText("Подтвердить")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(1000);
        Espresso.pressBackUnconditionally();
    }



//    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
//
//        return new RecyclerViewMatcher(recyclerViewId);
//    }
}
