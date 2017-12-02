package com.troshchiy.gitbook.di

import com.troshchiy.gitbook.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [NetworkModule::class])
    internal abstract fun mainActivity(): MainActivity
}