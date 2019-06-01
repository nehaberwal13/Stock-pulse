package com.app.criteria_parser.ui.criteria.value;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ValueProvider {
    @ContributesAndroidInjector(modules = {ValueFragmentModule.class})
    abstract ValueFragment provideValueFragmentFactory();
}
