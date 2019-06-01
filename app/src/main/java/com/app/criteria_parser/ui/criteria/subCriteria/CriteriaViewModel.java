package com.app.criteria_parser.ui.criteria.subCriteria;

import com.app.criteria_parser.data.DataManager;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.utils.CommonUtils;
import com.app.criteria_parser.utils.rx.SchedulerProvider;

import javax.inject.Inject;

import androidx.databinding.ObservableField;

public class CriteriaViewModel extends BaseViewModel<CriteriaFragmentNavigator> {

    private Example data;
    public CriteriaViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
//        this.data = data;
    }

//    public Example getData() {
//        return data;
//    }


}

