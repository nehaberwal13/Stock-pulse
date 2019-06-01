package com.app.criteria_parser.ui.criteria.value;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.criteria_parser.R;
import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.databinding.LayoutCriteriaListItemBinding;
import com.app.criteria_parser.databinding.LayoutValueListItemBinding;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public class ValueAdapter extends RecyclerView.Adapter<ValueAdapter.ValueViewHolder> {
    private ArrayList<Float> mList;

    private Context context;

    public ValueAdapter(Context context, List<Float> list) {
        this.context = context;
        this.mList = (ArrayList<Float>) list;

    }
    @NonNull
    @Override
    public ValueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutValueListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_value_list_item,
                        parent, false);
        return new ValueViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ValueViewHolder holder, int position) {
        holder.binding.setVariable(BR.viewModel, mList.get(position));
        holder.binding.executePendingBindings();
        holder.setData(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void clearItems() {
        if (mList != null)
            mList.clear();
    }

    public void addItems(List<Float> list) {
        mList.addAll(list);
        notifyDataSetChanged();

    }


    public class ValueViewHolder extends RecyclerView.ViewHolder {

        private LayoutValueListItemBinding binding;

        public ValueViewHolder(LayoutValueListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void setData(Float aFloat) {
            binding.setValue(aFloat);
            DecimalFormat format = new DecimalFormat();
            format.setDecimalSeparatorAlwaysShown(false);
            binding.titleTextView.setText(format.format(aFloat));
        }
    }
}
