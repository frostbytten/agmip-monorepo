package org.agmip.translators.core;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TranslatorSpecEntry {
    private List<String> modelKeys;
    private List<String> aceKeys;
    private final String unit;
    private final Function<String, String> fun;

    public TranslatorSpecEntry(String modelKeys, String aceKeys, String modelUnit, Function<String, String> fun, String keyDelim) {
        if (modelKeys.isBlank()) {
            throw new IllegalArgumentException("modelKeys cannot be blank.");
        }
        if (aceKeys.isBlank()) {
            throw new IllegalArgumentException("aceKeys cannot be blank.");
        }
        if (modelKeys.contains(keyDelim) && aceKeys.contains(keyDelim)) {
            throw new IllegalArgumentException("Both modelKeys and aceKeys cannot contain multiple keys.");
        }
        this.modelKeys = List.of(modelKeys.split(keyDelim));
        this.aceKeys = List.of(aceKeys.split(keyDelim));
        this.unit = modelUnit;
        this.fun = fun;
    }

    public List<String> getModelKeys() {
        return modelKeys;
    }

    public List<String> getAceKeys() {
        return aceKeys;
    }

    public String getUnit() {
        return unit;
    }

    public Function<String, String> getFun() {
        return fun;
    }

    private List<String> updatePath(String root, List<String> keys) {
        return keys.stream().map(s -> (s.startsWith("/") ? s : (root.endsWith("/") ? root + s : root + "/" + s))).collect(Collectors.toUnmodifiableList());
    }
    public void updateModelPath(String root) {
        this.modelKeys = updatePath(root, modelKeys);
    }

    public void updateAcePath(String root) {
        this.aceKeys = updatePath(root, aceKeys);
    }

    // Each entry can convert to a TranslatorPart
    public void toTranslatorPart(TranslationDirection dir) {
    }
}
