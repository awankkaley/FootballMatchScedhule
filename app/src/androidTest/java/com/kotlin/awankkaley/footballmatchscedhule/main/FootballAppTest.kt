package com.kotlin.awankkaley.footballmatchscedhule.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kotlin.awankkaley.footballmatchscedhule.R.id.*
import com.kotlin.awankkaley.footballmatchscedhule.main.idling.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FootballAppTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        activityRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun testRecyclerViewBehaviour() {

        val idlingResource = LastFragmentIdlingResource()
        IdlingRegistry.getInstance().register(idlingResource)
        onView(withId(rv_last))
            .check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResource)
        onView(withId(rv_last)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        onView(withId(rv_last)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))


        val idlingResourceDetail =
            DetailActivityIdlingResource()
        IdlingRegistry.getInstance().register(idlingResourceDetail)
        pressBack()
        IdlingRegistry.getInstance().unregister(idlingResourceDetail)

        onView(withId(view_pager))
            .check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(view_pager)).perform(swipeLeft())

        val idlingResourceNext = NextFragmentIdlingResource()
        IdlingRegistry.getInstance().register(idlingResourceNext)
        onView(withId(rv_next))
            .check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResourceNext)
        onView(withId(rv_next)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        Thread.sleep(2000)
        onView(withId(rv_next)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))

        Thread.sleep(2000)
        IdlingRegistry.getInstance().register(idlingResourceDetail)
        onView(withId(favo_menu_star)).perform(click())
        IdlingRegistry.getInstance().unregister(idlingResourceDetail)

        pressBack()

        onView(withId(search_menu_item_no)).perform(click())
        Thread.sleep(2000)
        onView(withId(search_menu_item)).perform(typeText("Barcelona"))

        val idlingResourceSearchMatch = SearchMatchIdlingResource()
        IdlingRegistry.getInstance().register(idlingResourceSearchMatch)
        onView(withId(rv_match_search)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResourceSearchMatch)
        onView(withId(rv_match_search)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        Thread.sleep(2000)
        onView(withId(rv_match_search)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        pressBack()
        pressBack()
        pressBack()

        onView(withId(menu_team)).perform(click())

        val idlingResourceTeam = TeamFragmentIdlingResource()
        IdlingRegistry.getInstance().register(idlingResourceTeam)
        onView(withId(rv_team)).check(matches(isDisplayed()))
        IdlingRegistry.getInstance().unregister(idlingResourceTeam)
        onView(withId(rv_team)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(1))
        Thread.sleep(2000)
        onView(withId(rv_team)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click())
        )
    }

}