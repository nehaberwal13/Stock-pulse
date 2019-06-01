package com.app.criteria_parser.ui.scanData;


import android.content.Context;

import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.utils.CommonUtils;

import java.util.List;

import androidx.databinding.ObservableField;

public class ScanDataItemViewModel {

    public final ObservableField<String> name;

    public final ObservableField<String> color;
    public int colorCode;

    public final ObservableField<String> tag;

    private Context context;
    public final Example data;
    private final ItemViewModelListener mListener;
    private List<Criteria> criteria;


    public ScanDataItemViewModel(Context context, Example data, ItemViewModelListener listener) {
        this.context = context;
        this.data = data;
        this.mListener = listener;
        name = new ObservableField<>(data.getName());
        color = new ObservableField<>(data.getColor());
        tag = new ObservableField<>(data.getTag());
        colorCode =CommonUtils.getColorCode(context,color.get());
        data.setColorCode(colorCode);
        getCreteria();
    }



    public List<Criteria> getCreteria() {
       criteria = data.getCriteria();
        return criteria;
    }

    public void onItemClick() {
        mListener.onItemClick(data);
    }

    public interface ItemViewModelListener {
        void onItemClick(Example data);
    }


}
