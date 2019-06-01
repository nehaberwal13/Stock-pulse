
package com.app.criteria_parser.data;


import com.app.criteria_parser.data.model.Example;

import java.util.List;

import io.reactivex.Single;

public interface DataManager  {
     Single<List<Example>> getscanDataApiCall();
}
