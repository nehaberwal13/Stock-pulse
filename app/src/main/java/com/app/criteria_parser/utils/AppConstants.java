

package com.app.criteria_parser.utils;


public final class AppConstants {


    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String WHITE = "white";
    public static final String GREEN = "green";
    public static final String RED = "red";
    public static final String BUNDLE_DATA = "data";

    public static final String VARIABLE = "variable";
    public static final String PLAIN_TEXT = "plain_text";

    public static final String VALUE = "value";
    public static final String INDICATOR = "indicator";
    public static final String BUNDLE_VARIABLE = "bundle_variable";


    private AppConstants() {
        // This utility class is not publicly instantiable
    }


    public static interface VARIABLE_KEYS {
        String TYPE = "type";
        String VALUES = "values";
        String STUDY_TYPE = "study_type";
        String PARAMETER_NAME = "parameter_name";
        String MIN_VALUE = "min_value";
        String MAX_VALUE = "max_value";
        String DEFAULT_VALUE = "default_value";

    }

}
