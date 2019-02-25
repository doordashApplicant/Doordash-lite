package com.doordash.doordash_lite.infra

import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry

open class BaseInstrumentationTest{
	val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
	val targetContext = ApplicationProvider.getApplicationContext<MyMockApp>()
	val dependencymanager =
		(targetContext as MyMockApp).getDependencymanager() as TestDependencyManager
}