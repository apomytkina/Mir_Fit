package ru.hse.project.mirfit.cucumber.steps;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import ru.hse.project.mirfit.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DeleteUser {

    //Нажатие кнопки удалить аккаунт
    @And("^I click delete user button$")
    public void clickDeleteUser() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_delete_user)).perform(click());
    }

    //Нажатие кнопки отменить удаление аккаунта
    @And("^I click cancel button in dialog$")
    public void clickCancelDialog() {
        SystemClock.sleep(3000);
        onView(withText("Отмена")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
    }

    //Нажатие кнопки удалить аккаунт после предупреждения
    @And("^I click accept button in dialog$")
    public void clickAcceptDialog() {
        SystemClock.sleep(3000);
        onView(withText("Подтвердить")).inRoot(isDialog()).check(matches(isDisplayed())).perform(click());
        SystemClock.sleep(3000);
        Espresso.pressBackUnconditionally();
    }

    //Выход из приложения после отмены удаления
    @Then("^I leave the app$")
    public void leaveApp() {
        SystemClock.sleep(3000);
        onView(withId(R.id.btn_sign_out)).perform(click());
        Espresso.pressBackUnconditionally();
    }

}
