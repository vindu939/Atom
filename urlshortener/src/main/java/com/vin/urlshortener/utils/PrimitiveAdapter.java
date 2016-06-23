package com.vin.urlshortener.utils;

import com.squareup.moshi.FromJson;
import com.sun.istack.internal.Nullable;

public class PrimitiveAdapter {

    @FromJson
    public int intFromJson(@Nullable Integer value) {
        if (value == null) {
            return 0;
        }

        return value;
    }

    @FromJson
    public float floatFromJson(@Nullable Float value) {
        if (value == null) {
            return 0;
        }

        return value;
    }

    @FromJson
    public long longFromJson(@Nullable Long value) {
        if (value == null) {
            return 0;
        }

        return value;
    }
}