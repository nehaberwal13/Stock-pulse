
package com.app.criteria_parser.utils;

import android.content.Context;
import android.widget.ImageView;

import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaAdapter;
import com.app.criteria_parser.ui.criteria.value.ValueAdapter;
import com.app.criteria_parser.ui.scanData.ScanDataAdapter;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;


public final class BindingUtils {

    private BindingUtils() {
        // This class is not publicly instantiable
    }
//
    @BindingAdapter({"adapter"})
    public static void addScanItems(RecyclerView recyclerView, List<Example> list) {
        ScanDataAdapter adapter = (ScanDataAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(list);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addCriteriaItems(RecyclerView recyclerView, List<Criteria> list) {
        CriteriaAdapter adapter = (CriteriaAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(list);
        }
    }

    @BindingAdapter({"adapter"})
    public static void addValueItems(RecyclerView recyclerView, List<Float> list) {
        ValueAdapter adapter = (ValueAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.clearItems();
            adapter.addItems(list);
        }
    }


}
