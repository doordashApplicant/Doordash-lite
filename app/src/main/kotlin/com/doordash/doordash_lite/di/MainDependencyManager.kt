package com.doordash.doordash_lite.di

import com.doordash.doordash_lite.source.DoordashService

class MainDependencyManager : DependencyManager {
	override fun getDoordashService(): DoordashService {
		return DoordashService()
	}
}