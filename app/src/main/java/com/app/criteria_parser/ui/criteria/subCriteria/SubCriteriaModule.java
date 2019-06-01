package com.app.criteria_parser.ui.criteria.subCriteria;

import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.scanData.ScanDataActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SubCriteriaModule {
    @Provides
    CriteriaAdapter provideAdapter(CriteriaActivity activity) {
        return new CriteriaAdapter(activity,new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CriteriaActivity activity) {
        return new LinearLayoutManager(activity);

    }
}