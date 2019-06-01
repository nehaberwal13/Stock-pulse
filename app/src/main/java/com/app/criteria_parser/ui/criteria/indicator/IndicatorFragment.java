package com.app.criteria_parser.ui.criteria.indicator;

import android.os.Bundle;
import android.view.View;

import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.data.model.Variable;
import com.app.criteria_parser.databinding.FragmentIndicatorBinding;
import com.app.criteria_parser.ui.base.BaseFragment;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaFragment;
import com.app.criteria_parser.utils.AppConstants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class IndicatorFragment extends BaseFragment<FragmentIndicatorBinding, BaseViewModel> {
    FragmentIndicatorBinding fragmentIndicatorBinding;
    private Variable variable;

    public static IndicatorFragment newInstance(Bundle args) {
        IndicatorFragment fragment = new IndicatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_indicator;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDataFromViewModel(false);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentIndicatorBinding=getViewDataBinding();
        variable = getArguments().getParcelable(AppConstants.BUNDLE_VARIABLE);
        fragmentIndicatorBinding.setViewModel(variable);
    }
}
