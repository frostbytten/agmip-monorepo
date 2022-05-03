package org.agmip.translators.core;

import java.util.function.Function;

public class TranslatorSpecEntry {
    private final String key;
    private final String unit;
    private final Function<String, String> fun;
    private boolean specFulfilled = false;

    public TranslatorSpecEntry(String key, String unit) {
        this.key = key;
        this.unit = unit;
        this.fun = null;
    }

    public TranslatorSpecEntry(String key, Function<String, String> fun) {
        this.key = key;
        this.unit = null;
        this.fun = fun;
    }

    public boolean isFulfilled() {
        return specFulfilled;
    }
}
