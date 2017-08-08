package com.android.mucha.rssreader

import android.app.Application
import com.android.mucha.rssreader.app.main.MainActivityModule
import com.android.mucha.rssreader.app.settings.SettingsFeedsActivityModule
import com.android.mucha.rssreader.database.DatabaseModule
import com.android.mucha.rssreader.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        DatabaseModule::class,
        ViewModelModule::class,

        // Activity Modules
        MainActivityModule::class,
        SettingsFeedsActivityModule::class
))
@Singleton
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    fun inject(application: RSSReaderApplication)
}