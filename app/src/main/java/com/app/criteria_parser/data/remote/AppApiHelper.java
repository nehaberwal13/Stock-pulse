
package com.app.criteria_parser.data.remote;


import com.app.criteria_parser.data.model.Example;

import java.util.List;

import io.reactivex.Single;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class AppApiHelper implements ApiHelper {
    @Inject
    RetrofitHelper helper;

    @Inject
    public AppApiHelper() {
    }


    @Override
    public Single<List<Example>> getscanDataApiCall() {
        return helper.getApiHelper().getscanDataApiCall();
    }
}
