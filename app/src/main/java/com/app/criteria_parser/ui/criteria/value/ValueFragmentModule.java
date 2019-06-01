package com.app.criteria_parser.ui.criteria.value;

import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaAdapter;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ValueFragmentModule {

    @Provides
    ValueAdapter provideAdapter(CriteriaActivity activity) {
        return new ValueAdapter(activity,new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CriteriaActivity activity) {
        return new LinearLayoutManager(activity);

    }
}