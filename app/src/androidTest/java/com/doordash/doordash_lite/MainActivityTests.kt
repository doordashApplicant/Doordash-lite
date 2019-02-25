package com.doordash.doordash_lite

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.doordash.doordash_lite.infra.BaseInstrumentationTest
import com.doordash.doordash_lite.infra.TestDependencyManager
import com.doordash.doordash_lite.views.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTests : BaseInstrumentationTest() {

	@Rule
	@JvmField
	val activity = ActivityTestRule<MainActivity>(
		MainActivity::class.java,
		false,
		false
	)

	@Test
	fun loadingIndicatorIsDisplayed() {
		dependencymanager.changeResultState(TestDependencyManager.ResultState.NoResult)

		activity.launchActivity(Intent(context, MainActivity::class.java))

		onView(withId(R.id.loadingIndicator)).check(matches(isDisplayed()))
	}

	@Test
	fun loadingIndicatorShouldNotBeVisibleWhenTheRequestResults() {
		dependencymanager.changeResultState(TestDependencyManager.ResultState.FewResults)

		activity.launchActivity(Intent(context, MainActivity::class.java))

		onView(withId(R.id.loadingIndicator)).check(matches(not(isDisplayed())))
	}
}
