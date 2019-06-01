package com.app.criteria_parser;


import com.app.criteria_parser.data.DataManager;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaViewModel;
import com.app.criteria_parser.ui.scanData.ScanDataViewModel;
import com.app.criteria_parser.ui.splash.SplashViewModel;
import com.app.criteria_parser.utils.rx.SchedulerProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


@Singleton
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager,
                                    SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            //noinspection unchecked
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ScanDataViewModel.class)) {

            return (T) new ScanDataViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CriteriaViewModel.class)) {
            return (T) new CriteriaViewModel(dataManager, schedulerProvider);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}