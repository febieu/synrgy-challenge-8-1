package com.example.ch8.presentation.auth.login

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(LoginActivity::class.java)

//    @Test
//    fun testViewIsVisible() {
//        Espresso.onView(
//            ViewMatchers.withId(R.id.header)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.isDisplayed()
//            )
//        )
//        Espresso.onView(
//            ViewMatchers.withId(R.id.et_username)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.isDisplayed()
//            )
//        )
//        Espresso.onView(
//            ViewMatchers.withId(R.id.et_password)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.isDisplayed()
//            )
//        )
//        Espresso.onView(
//            ViewMatchers.withId(R.id.button_login)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.isDisplayed()
//            )
//        )
//        Espresso.onView(
//            ViewMatchers.withId(R.id.button_register)
//        ).check(
//            ViewAssertions.matches(
//                ViewMatchers.isDisplayed()
//            )
//        )
//    }
}
