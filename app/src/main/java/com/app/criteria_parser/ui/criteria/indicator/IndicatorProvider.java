package com.app.criteria_parser.ui.criteria.indicator;

import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaFragment;
import com.app.criteria_parser.ui.criteria.subCriteria.SubCriteriaModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class IndicatorProvider {
    @ContributesAndroidInjector
    abstract IndicatorFragment provideIndicatorFragment();
}
