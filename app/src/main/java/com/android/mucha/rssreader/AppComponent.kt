package com.android.mucha.rssreader

import com.android.mucha.rssreader.database.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(
        AppModule::class,
        DatabaseModule::class
))
@Singleton
interface AppComponent {
}