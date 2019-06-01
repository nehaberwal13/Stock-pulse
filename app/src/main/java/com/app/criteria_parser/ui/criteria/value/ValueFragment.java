package com.app.criteria_parser.ui.criteria.value;

import android.os.Bundle;
import android.view.View;

import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.data.model.Variable;
import com.app.criteria_parser.databinding.FragmentValueBinding;
import com.app.criteria_parser.ui.base.BaseFragment;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaAdapter;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaFragment;
import com.app.criteria_parser.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ValueFragment extends BaseFragment<FragmentValueBinding, BaseViewModel> {
    FragmentValueBinding fragmentValueBinding;

    @Inject
    ValueAdapter adapter;

    @Inject
    LinearLayoutManager mLayoutManger;

    public static ValueFragment newInstance(Bundle args) {
        ValueFragment fragment = new ValueFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_value;
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
        fragmentValueBinding = getViewDataBinding();
        Variable variable = (Variable) getArguments().getParcelable(AppConstants.BUNDLE_VARIABLE);
        fragmentValueBinding.setViewModel(variable);
        setView();
    }

    private void setView() {
        mLayoutManger.setOrientation(RecyclerView.VERTICAL);
        fragmentValueBinding.rv.setLayoutManager(mLayoutManger);
        fragmentValueBinding.rv.setItemAnimator(new DefaultItemAnimator());
        fragmentValueBinding.rv.setAdapter(adapter);
    }
}
