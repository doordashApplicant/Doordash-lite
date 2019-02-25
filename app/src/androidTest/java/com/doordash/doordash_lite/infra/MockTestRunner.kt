package com.doordash.doordash_lite.infra

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner


class MockTestRunner : AndroidJUnitRunner() {
	@Throws(
		InstantiationException::class,
		IllegalAccessException::class,
		ClassNotFoundException::class
	)
	override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
		return super.newApplication(cl, MyMockApp::class.java.name, context)
	}
}