package com.app.criteria_parser.ui.scanData;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.criteria_parser.R;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.databinding.LayoutListItemBinding;
import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.utils.AppConstants;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class ScanDataAdapter extends RecyclerView.Adapter<ScanDataAdapter.RequestViewHolder> implements ScanDataItemViewModel.ItemViewModelListener  {
    List<Example> mList;
    private Context context;

    public ScanDataAdapter(Context context) {

        this.context = context;
    }
    public void setRequestList(final List<Example> list) {
        if (mList == null) {
            mList = list;
            notifyItemRangeInserted(0, list.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mList.get(oldItemPosition).getId() ==
                            list.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Example data = list.get(newItemPosition);
                    Example oldData = mList.get(oldItemPosition);
                    return data.getId() == oldData.getId()
                            && Objects.equals(data.getName(), oldData.getName())
                            && Objects.equals(data.getTag(), oldData.getTag());
                }
            });
            mList = list;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_list_item,
                        parent, false);
        return new RequestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        holder.binding.setViewModel(new ScanDataItemViewModel(context,mList.get(position),this::onItemClick));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void clearItems() {
        mList.clear();
    }

    public void addItems(List<Example> list) {
        mList.addAll(list);
        notifyDataSetChanged();

    }

    @Override
    public void onItemClick(Example data) {
        if (data != null) {
            Bundle  bundle =new Bundle();
            bundle.putParcelable(AppConstants.BUNDLE_DATA ,data);
            Intent intent = CriteriaActivity.newIntent(context);
            intent.putExtra(AppConstants.BUNDLE_DATA,bundle);
            context.startActivity(intent);

        }
    }
    public class RequestViewHolder extends  RecyclerView.ViewHolder {

        private LayoutListItemBinding binding;


        public RequestViewHolder(LayoutListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
