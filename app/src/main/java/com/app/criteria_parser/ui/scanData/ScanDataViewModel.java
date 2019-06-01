package com.app.criteria_parser.ui.scanData;


import com.app.criteria_parser.data.DataManager;
import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.utils.rx.SchedulerProvider;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ScanDataViewModel extends BaseViewModel<ScanDataNavigator> {

    private final MutableLiveData<List<Example>> mRequest;
    public ScanDataViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mRequest = new MutableLiveData<>();;
        fetchRequest();
    }

    public void fetchRequest() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getscanDataApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(result -> {
                    for(Example example:result){
                        for(Criteria criteria:example.getCriteria()){
                            if(criteria.getVariable()!=null)
                            criteria.setVariableString(criteria.getVariable().toString());
                        }

                    }
                    mRequest.setValue(result);
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
//                    getNavigator().handleError(throwable);
                }));


    }

    public LiveData<List<Example>> getReqData() {
        return mRequest;
    }


}
