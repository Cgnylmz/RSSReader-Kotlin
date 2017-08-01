package com.android.mucha.rssreader.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.mucha.rssreader.app.main.MainViewModel
import com.android.mucha.rssreader.app.settings.SettingsFeedsViewModel
import com.android.mucha.rssreader.dagger.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsFeedsViewModel::class)
    abstract fun bindSettingsFeedsViewModel(viewModel: SettingsFeedsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}