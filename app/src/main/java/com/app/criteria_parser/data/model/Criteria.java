package com.app.criteria_parser.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.app.criteria_parser.utils.GsonUtils;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Set;

public class Criteria implements Parcelable {

    private String type;
    private String text;
    private JsonObject variable;
    private String variableString;

    protected Criteria(Parcel in) {
        type = in.readString();
        text = in.readString();
        variableString = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(text);
        dest.writeString(variableString);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Criteria> CREATOR = new Creator<Criteria>() {
        @Override
        public Criteria createFromParcel(Parcel in) {
            return new Criteria(in);
        }

        @Override
        public Criteria[] newArray(int size) {
            return new Criteria[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVariableString() {
        return variableString;
    }

    public void setVariableString(String variableString) {
        this.variableString = variableString;
    }

    public JsonObject getVariable() {
        return variable;
    }

    public void setVariable(JsonObject variable) {
        this.variable = variable;
    }
}