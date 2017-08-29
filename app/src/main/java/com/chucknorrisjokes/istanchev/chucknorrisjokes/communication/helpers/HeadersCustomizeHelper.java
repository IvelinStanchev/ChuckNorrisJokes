package com.chucknorrisjokes.istanchev.chucknorrisjokes.communication.helpers;

import java.util.HashMap;

import okhttp3.Headers;

/**
 * Created by istanchev on 8/29/17.
 */

public class HeadersCustomizeHelper {

    private static final String CONTENT_TYPE_NAME_HEADER = "Content-Type";
    private static final String CONTENT_TYPE_JSON_VALUE_HEADER = "application/json";

    public static Headers getAppHeaders() {
        HashMap<String, String> headersMap = new HashMap<>();

        headersMap.put(CONTENT_TYPE_NAME_HEADER, CONTENT_TYPE_JSON_VALUE_HEADER);

        return Headers.of(headersMap);
    }
}
