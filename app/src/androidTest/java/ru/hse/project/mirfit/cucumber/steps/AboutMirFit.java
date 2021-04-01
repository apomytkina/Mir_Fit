package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;

import cucumber.api.java.en.And;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AboutMirFit {

    //Нажатие кнопки об мир фит
    @And("^I click about mir fit button$")
    public void clickAboutMirFit() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_about_mir_fit)).perform(click());
    }

    public ViewAction customswipeDown() {
        return new GeneralSwipeAction(Swipe.SLOW, GeneralLocation.TOP_CENTER, GeneralLocation.BOTTOM_CENTER, Press.FINGER);
    }

    //Делаем свайп вниз
    @And("^I swipe it down$")
    public void swipeDown() {
        SystemClock.sleep(3000);
        onView(withId(R.id.prof_set_back_content)).perform(customswipeDown());
    }

    //Выходим из приложения
    @And("^I go out from app$")
    public void goOut() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_sign_out)).perform(click());
        Espresso.pressBackUnconditionally();
    }
}
