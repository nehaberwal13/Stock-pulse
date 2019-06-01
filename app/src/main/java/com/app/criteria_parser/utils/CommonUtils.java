
package com.app.criteria_parser.utils;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.util.Patterns;

import com.app.criteria_parser.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public final class CommonUtils {

    private static int colorCode;

    private CommonUtils() {
        // This utility class is not publicly instantiable
    }

    public static int getColorCode(Context context, String color) {
        switch (color) {
            case AppConstants.RED:
                colorCode = context.getColor(R.color.red);
                break;
            case AppConstants.GREEN:
                colorCode = context.getColor(R.color.light_green);
                break;
            case AppConstants.WHITE:
                colorCode = context.getColor(R.color.white);
                break;
            default:
                colorCode = context.getColor(R.color.blue_black_dark);
                break;

        }
        return colorCode;

    }

    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }
}
