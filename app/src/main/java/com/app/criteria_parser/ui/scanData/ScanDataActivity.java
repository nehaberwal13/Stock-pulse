package com.app.criteria_parser.ui.scanData;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.ViewModelProviderFactory;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.databinding.ActivityScanDataBinding;
import com.app.criteria_parser.ui.base.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ScanDataActivity extends BaseActivity<ActivityScanDataBinding, ScanDataViewModel> {
    @Inject
    ScanDataAdapter adapter;

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    LinearLayoutManager mLayoutManger;

    ScanDataViewModel viewModel;

    ActivityScanDataBinding mBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, ScanDataActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return  BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_data;
    }

    @Override
    public ScanDataViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, factory).get(ScanDataViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mBinding = getViewDataBinding();
        setView();
        subscribeUi();
    }

    private void setView() {
        mLayoutManger.setOrientation(RecyclerView.VERTICAL);
        mBinding.rv.setLayoutManager(mLayoutManger);
        mBinding.rv.setItemAnimator(new DefaultItemAnimator());
        mBinding.rv.setAdapter(adapter);
    }

    private void subscribeUi() {
        // Update the list when the data changes
        viewModel.getReqData().observe(this, new Observer<List<Example>>() {
            @Override
            public void onChanged(List<Example> results) {
                if (results != null) {
//                    mBinding.setIsLoading(false);
                    adapter.setRequestList(results);
                } else {
//                    mBinding.setIsLoading(true);
                }
                // espresso does not know how to wait for data binding's loop so we execute changes
                // sync.
                mBinding.executePendingBindings();
            }

        });
    }


}
