
package com.app.criteria_parser.ui.criteria;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.ViewModelProviderFactory;
import com.app.criteria_parser.data.model.Example;
import com.app.criteria_parser.databinding.ActivityCriteriaBinding;
import com.app.criteria_parser.ui.base.BaseActivity;
import com.app.criteria_parser.ui.base.BaseFragment;
import com.app.criteria_parser.ui.criteria.subCriteria.CriteriaFragment;
import com.app.criteria_parser.ui.scanData.ScanDataViewModel;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

import static com.app.criteria_parser.utils.AppConstants.BUNDLE_DATA;

public class CriteriaActivity extends BaseActivity<ActivityCriteriaBinding, ScanDataViewModel> implements CriteriaNavigator, HasSupportFragmentInjector {

    private Example data;

    public static Intent newIntent(Context context) {
        return new Intent(context, CriteriaActivity.class);
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;


    @Inject
    ViewModelProviderFactory factory;
    private ScanDataViewModel criteriaViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_criteria;
    }

    @Override
    public ScanDataViewModel getViewModel() {
        criteriaViewModel = ViewModelProviders.of(this, factory).get(ScanDataViewModel.class);
        return criteriaViewModel;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=(Example)getIntent().getExtras().getBundle(BUNDLE_DATA).getParcelable(BUNDLE_DATA);
        showFragment( CriteriaFragment.newInstance(getIntent().getBundleExtra(BUNDLE_DATA)),false);
    }

    public Example getData() {
        return data;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
    public void showFragment(BaseFragment baseFragment,boolean addToBackstack) {
        FragmentTransaction add = getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.container, baseFragment, CriteriaFragment.TAG);
        if (addToBackstack)
                add.addToBackStack(baseFragment.getTag()).commit();
              else
                add.commit();

    }
}
