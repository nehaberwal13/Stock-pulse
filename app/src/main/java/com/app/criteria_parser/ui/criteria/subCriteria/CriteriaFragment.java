package com.app.criteria_parser.ui.criteria.subCriteria;

import android.os.Bundle;
import android.view.View;

import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.ViewModelProviderFactory;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.databinding.FragmentCriteriaBinding;
import com.app.criteria_parser.ui.base.BaseFragment;
import com.app.criteria_parser.ui.base.BaseViewModel;
import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.scanData.ScanDataViewModel;
import com.app.criteria_parser.utils.AppConstants;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CriteriaFragment extends BaseFragment<FragmentCriteriaBinding, BaseViewModel> {

    public static final String TAG = CriteriaFragment.class.getSimpleName();

    FragmentCriteriaBinding fragmentCriteriaBinding;
    @Inject
    CriteriaAdapter adapter;

    @Inject
    LinearLayoutManager mLayoutManger;
    @Inject
    ViewModelProviderFactory factory;
    private CriteriaViewModel criteriaViewModel;

    public static CriteriaFragment newInstance(Bundle args) {
        CriteriaFragment fragment = new CriteriaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_criteria;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isDataFromViewModel(false);
//        adapter.setRequestList(((CriteriaActivity) getActivity()).getData().getCriteria());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentCriteriaBinding = getViewDataBinding();
        fragmentCriteriaBinding.setViewModel(((CriteriaActivity) getActivity()).getData());
        setView();
    }

    private void setView() {
        mLayoutManger.setOrientation(RecyclerView.VERTICAL);
        fragmentCriteriaBinding.rv.setLayoutManager(mLayoutManger);
        fragmentCriteriaBinding.rv.setItemAnimator(new DefaultItemAnimator());
        fragmentCriteriaBinding.rv.setAdapter(adapter);
    }

}
