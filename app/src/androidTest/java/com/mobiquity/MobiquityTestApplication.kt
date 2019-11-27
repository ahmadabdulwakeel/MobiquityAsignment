package com.mobiquity

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.github.tmurakami.dexopener.DexOpener
import org.koin.core.context.startKoin


class MobiquityTestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                emptyList()
            )
        }

    }
}

class TestAppJUnitRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        DexOpener.install(this)
        return super.newApplication(cl, MobiquityTestApplication::class.java.name, context)
    }
}