
package com.app.criteria_parser.data;

import android.content.Context;

import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.data.remote.ApiHelper;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {


    private final ApiHelper mApiHelper;

    //    private final Context mContext;
    private final Gson mGson;

    @Inject
    public AppDataManager(ApiHelper apiHelper, Gson gson) {
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Single<List<Example>> getscanDataApiCall() {
        return mApiHelper.getscanDataApiCall();
    }
}
