package com.app.criteria_parser.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Variable implements Parcelable {

    private String type;
    private List<Float> values = null;
    private String study_type;
    private String parameter_name;
    private Integer min_value;
    private Integer max_value;
    private Integer default_value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Float> getValues() {
        return values;
    }

    public void setValues(List<Float> values) {
        this.values = values;
    }

    public String getStudy_type() {
        return study_type;
    }

    public void setStudy_type(String study_type) {
        this.study_type = study_type;
    }

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public Integer getMin_value() {
        return min_value;
    }

    public void setMin_value(Integer min_value) {
        this.min_value = min_value;
    }

    public Integer getMax_value() {
        return max_value;
    }

    public void setMax_value(Integer max_value) {
        this.max_value = max_value;
    }

    public Integer getDefault_value() {
        return default_value;
    }

    public void setDefault_value(Integer default_value) {
        this.default_value = default_value;
    }

    protected Variable(Parcel in) {
        type = in.readString();
        study_type = in.readString();
        parameter_name = in.readString();
        if (in.readByte() == 0) {
            min_value = null;
        } else {
            min_value = in.readInt();
        }
        if (in.readByte() == 0) {
            max_value = null;
        } else {
            max_value = in.readInt();
        }
        if (in.readByte() == 0) {
            default_value = null;
        } else {
            default_value = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(study_type);
        dest.writeString(parameter_name);
        if (min_value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(min_value);
        }
        if (max_value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(max_value);
        }
        if (default_value == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(default_value);
        }
    }

    public static final Creator<Variable> CREATOR = new Creator<Variable>() {
        @Override
        public Variable createFromParcel(Parcel in) {
            return new Variable(in);
        }

        @Override
        public Variable[] newArray(int size) {
            return new Variable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }


}
