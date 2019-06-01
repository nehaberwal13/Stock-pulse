package com.app.criteria_parser.ui.criteria.subCriteria;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class SubCriteriaFragmentProvider {

    @ContributesAndroidInjector(modules = {SubCriteriaModule.class})
    abstract CriteriaFragment provideCriteriaFragmentFactory();
}
