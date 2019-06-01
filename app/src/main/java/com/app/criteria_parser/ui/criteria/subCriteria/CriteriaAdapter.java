package com.app.criteria_parser.ui.criteria.subCriteria;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.criteria_parser.R;
import com.app.criteria_parser.data.model.Criteria;
import com.app.criteria_parser.data.model.Variable;
import com.app.criteria_parser.databinding.LayoutCriteriaListItemBinding;
import com.app.criteria_parser.databinding.LayoutListItemBinding;
import com.app.criteria_parser.ui.criteria.CriteriaActivity;
import com.app.criteria_parser.ui.criteria.indicator.IndicatorFragment;
import com.app.criteria_parser.ui.criteria.value.ValueFragment;
import com.app.criteria_parser.utils.AppConstants;
import com.app.criteria_parser.utils.GsonUtils;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CriteriaAdapter extends RecyclerView.Adapter<CriteriaAdapter.CriteriaViewHolder> {
    List<Criteria> mList;
    private CriteriaActivity context;

    public CriteriaAdapter(CriteriaActivity context, List<Criteria> list) {
        this.context = context;
        this.mList = list;

    }

    @NonNull
    @Override
    public CriteriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCriteriaListItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_criteria_list_item,
                        parent, false);
        return new CriteriaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CriteriaViewHolder holder, int position) {
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

    public void addItems(List<Criteria> list) {
        mList.addAll(list);
        notifyDataSetChanged();

    }

    public class CriteriaViewHolder extends RecyclerView.ViewHolder {

        private LayoutCriteriaListItemBinding binding;

        public CriteriaViewHolder(LayoutCriteriaListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }

        public void setData(Criteria criteria) {
            if (criteria.getType().equals(AppConstants.PLAIN_TEXT)) {
                binding.titleTextView.setText(criteria.getText());
            } else if (criteria.getType().equals(AppConstants.VARIABLE)) {
                String variableString = criteria.getVariableString();
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(variableString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HashMap<String, JSONObject> map = new HashMap<>();
                if (jsonObject != null) {
                    Iterator<String> keys = jsonObject.keys();
                    if (keys != null) {
                        for (Iterator<String> it = keys; it.hasNext(); ) {
                            String key = it.next();
                            try {
                                map.put(key, (JSONObject) jsonObject.get(key));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
                Set<String> keystrings = map.keySet();
                String text = criteria.getText();
                Map<String,String> keyMap =new HashMap<>();
                SpannableString ss = new SpannableString(text);
                DecimalFormat format = new DecimalFormat();
                format.setDecimalSeparatorAlwaysShown(false);
                boolean isBalanced=false;
                int diff=0;
                for (String ky : keystrings) {
                    Bundle bundle = new Bundle();
                    Variable variable = createVariable(map.get(ky));
                    String type = variable.getType();
                    if (type.equals(AppConstants.VALUE)) {
                        keyMap.put(ky,"("+format.format(variable.getValues().get(0))+")");
//                        keyMap.put(ky,Float.toString(variable.getValues().get(0)));
                    } else if (type.equals(AppConstants.INDICATOR)) {
                        keyMap.put(ky,"("+format.format(variable.getDefault_value())+")");
                    }
                    ClickableSpan clickableSpan = new ClickableSpan() {
                        @Override
                        public void onClick(View textView) {
                                bundle.putParcelable(AppConstants.BUNDLE_VARIABLE, variable);
                                if (type.equals(AppConstants.VALUE)) {
                                    context.showFragment(ValueFragment.newInstance(bundle),true);
                                } else if (type.equals(AppConstants.INDICATOR)) {
                                    context.showFragment(IndicatorFragment.newInstance(bundle),true);
                                }


                        }

                        @Override
                        public void updateDrawState(TextPaint ds) {
                            super.updateDrawState(ds);
                            ds.setUnderlineText(false);
                            ds.setColor(context.getColor(R.color.color_0080B1));
                        }
                    };
                    int indexfirst = text.indexOf(ky);
                    int indexSecond = indexfirst + ky.length();
                    if(!isBalanced) {
                        indexfirst=indexfirst+diff;
                        indexSecond=indexSecond+diff;
                    }

                    ss.setSpan(clickableSpan, indexfirst, indexSecond, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                }
                SpannableStringBuilder ssb =new SpannableStringBuilder();
                ssb.append(ss);


                for(String ky: keyMap.keySet()){
                    int indexfirst = text.indexOf(ky);
                    int indexSecond = indexfirst + ky.length();
                    if(!isBalanced) {
                        indexfirst=indexfirst+diff;
                        indexSecond=indexSecond+diff;
                    }
                    if(keyMap.get(ky).length()==ky.length()){
                        isBalanced =true;
                    }else {
                        isBalanced=false;
                        diff= keyMap.get(ky).length()-ky.length();
                    }
                    ssb.replace(indexfirst,indexSecond,keyMap.get(ky));
                }

                binding.titleTextView.setText(ssb);
                binding.titleTextView.setMovementMethod(LinkMovementMethod.getInstance());
                binding.titleTextView.setHighlightColor(Color.TRANSPARENT);
            }
        }
    }

    private Variable createVariable(JSONObject jsonObject){
        Variable variable1 = GsonUtils.parseJson(jsonObject.toString(), Variable.class);
        return variable1;
    }
}
