
package com.app.criteria_parser.ui.splash;


import com.app.criteria_parser.data.DataManager;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.utils.rx.SchedulerProvider;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    private final MutableLiveData<List<Example>> mRequest;
    public SplashViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        mRequest = new MutableLiveData<>();
    }



    public void startSeeding() {
        getCompositeDisposable().add(getDataManager()
                .getscanDataApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(result -> {
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

    public void decideNextActivity() {
//        if (getDataManager().getCurrentUserLoggedInMode() == DataManager.LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType()) {
//            getNavigator().openLoginActivity();
//        } else {
        getNavigator().openMainActivity();
//        }
    }
}
