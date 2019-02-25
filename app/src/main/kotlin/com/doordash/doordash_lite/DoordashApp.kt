package com.doordash.doordash_lite

import android.app.Application
import com.doordash.doordash_lite.di.DependencyManager
import com.doordash.doordash_lite.di.MainDependencyManager

open class DoordashApp:Application(){
	open fun getDependencymanager(): DependencyManager = MainDependencyManager()
}