package com.doordash.doordash_lite.infra

import com.doordash.doordash_lite.DoordashApp
import com.doordash.doordash_lite.di.DependencyManager

class MyMockApp : DoordashApp() {
	val testDependencyManager = TestDependencyManager()

	override fun getDependencymanager(): DependencyManager {
		return testDependencyManager
	}
}