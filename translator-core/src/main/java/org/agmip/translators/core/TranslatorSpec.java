package org.agmip.translators.core;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.agmip.translators.core.TranslatorSpecEntry;

public class TranslatorSpec {
    Map<String, TranslatorSpecEntry> toModelSpec = new HashMap<>();
    Map<String, TranslatorSpecEntry> fromModelSpec = new HashMap<>();
    private TranslatorSpec(Builder builder) {
        this.toModelSpec = builder.toModelSpec;
        this.fromModelSpec = builder.fromModelSpec;
    }

    public static class Builder {
        String modelDefaultPath;
        String aceDefaultPath;
        Map<String, TranslatorSpecEntry> toModelSpec = new HashMap<>();
        Map<String, TranslatorSpecEntry> fromModelSpec = new HashMap<>();
        // Model Default Path (can be overriden in key)
        public Builder modelDefaultPath(String path) {
            return this;
        }
        // ACE Default Path (can be overriden in key)
        public Builder aceDefaultPath(String path) {
            return this;
        }
        // Bidirectional mapping (cannot include a function here)
        public Builder map(String modelKey, String aceKey, String modelUnit) {
            return this;
        }
        // Mapping from the model to ACE
        public Builder fromModel(String aceKey, String modelKey, String modelUnit) {
            return this;
        }
        public Builder fromModel(String aceKey, String modelKeys, Function<String, String> fun) {
            return this; //pipe seperated
        }
        public Builder fromModel(String aceKey, String modelKeys, Function<String, String> fun, String keyDelimiter) {
            return this;
        } //user-specified seperator
        //Mapping from ACE to the model
        public Builder toModel(String modelKey, String aceKey, String modelUnit) {
            return this;
        }
        public Builder toModel(String modelKey, String aceKeys, Function<String, String> fun) {
            return this;
        }
        public Builder toModel(String modelKey, String aceKeys, Function<String, String> fun, String keyDelimiter) {
            return this;
        }
        public void validate(){}
        public TranslatorSpec build() {
            return new TranslatorSpec(this);
        }
    } 
}
