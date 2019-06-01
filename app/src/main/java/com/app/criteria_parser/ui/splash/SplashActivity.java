
package com.app.criteria_parser.ui.splash;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


import com.app.criteria_parser.BR;
import com.app.criteria_parser.R;
import com.app.criteria_parser.ViewModelProviderFactory;
import com.app.criteria_parser.databinding.ActivitySplashBinding;
import com.app.criteria_parser.ui.base.BaseActivity;
import com.app.criteria_parser.ui.scanData.ScanDataActivity;

import javax.inject.Inject;

import androidx.lifecycle.ViewModelProviders;


public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    ViewModelProviderFactory factory;
    private SplashViewModel mSplashViewModel;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        mSplashViewModel = ViewModelProviders.of(this,factory).get(SplashViewModel.class);
        return mSplashViewModel;
    }


    @Override
    public void openMainActivity() {
        Intent intent = ScanDataActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSplashViewModel.setNavigator(this);
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashViewModel.decideNextActivity();
            }
        },2000);

    }
}
