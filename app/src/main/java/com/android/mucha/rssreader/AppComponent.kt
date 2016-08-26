package com.android.mucha.rssreader

import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(
        AppModule::class
))
@Singleton
interface AppComponent {
}