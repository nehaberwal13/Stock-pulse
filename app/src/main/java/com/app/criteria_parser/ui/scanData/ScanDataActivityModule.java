package com.app.criteria_parser.ui.scanData;

import androidx.recyclerview.widget.LinearLayoutManager;
import dagger.Module;
import dagger.Provides;

@Module
public class ScanDataActivityModule {
    @Provides
    ScanDataAdapter provideAdapter(ScanDataActivity activity) {
        return new ScanDataAdapter(activity);
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(ScanDataActivity activity) {
        return new LinearLayoutManager(activity);

    }
}
