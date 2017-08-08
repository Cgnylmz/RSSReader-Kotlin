package com.android.mucha.rssreader.app.settings

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFeedsActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSettingsFeedsActivityInjector(): SettingsFeedsActivity
}