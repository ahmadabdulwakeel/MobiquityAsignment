package com.mobiquity.infrastructure.platform
import androidx.multidex.MultiDexApplication
import com.mobiquity.BuildConfig
import com.mobiquity.di.productModule.productRepositoryModule
import com.mobiquity.di.productModule.productUseCaseModule
import com.mobiquity.di.productModule.productViewModelModule
import com.mobiquity.infrastructure.networks.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MobiquityApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            androidContext(this@MobiquityApplication)
            modules(
                listOf(
                    platformModule,
                    networkModule,
                    productRepositoryModule,
                    productViewModelModule,
                    productUseCaseModule
                )
            )
        }

    }


}

