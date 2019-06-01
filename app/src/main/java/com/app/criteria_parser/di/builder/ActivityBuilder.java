
package com.app.criteria_parser.di.builder;


import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.criteria.indicator.IndicatorProvider;
import com.app.criteria_parser.ui.criteria.subCriteria.SubCriteriaFragmentProvider;
//import com.app.criteria_parser.ui.criteria.value.ValueProvider;
import com.app.criteria_parser.ui.criteria.value.ValueProvider;
import com.app.criteria_parser.ui.scanData.ScanDataActivity;
import com.app.criteria_parser.ui.scanData.ScanDataActivityModule;
import com.app.criteria_parser.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();


    @ContributesAndroidInjector(modules = {ScanDataActivityModule.class})
    abstract ScanDataActivity bindScanDataActivity();

    @ContributesAndroidInjector(modules = {SubCriteriaFragmentProvider.class, ValueProvider.class, IndicatorProvider.class})
    abstract CriteriaActivity bindCriteriaActivity();
}
