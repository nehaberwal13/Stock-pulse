
package com.app.criteria_parser.data.remote;



import com.app.criteria_parser.data.model.Example;

import java.util.List;

import androidx.room.Query;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiHelper {

    @GET("data")
    Single<List<Example>> getscanDataApiCall();

}
