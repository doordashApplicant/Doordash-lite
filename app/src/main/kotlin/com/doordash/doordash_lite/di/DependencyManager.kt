package com.doordash.doordash_lite.di

import com.doordash.doordash_lite.source.DoordashService

interface DependencyManager {
	fun getDoordashService(): DoordashService
}

