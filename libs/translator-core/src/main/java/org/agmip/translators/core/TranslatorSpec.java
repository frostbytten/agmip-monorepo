package org.agmip.translators.core;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TranslatorSpec {
    private List<TranslatorSpecEntry> toModelSpec = new ArrayList<>();
    private List<TranslatorSpecEntry> fromModelSpec = new ArrayList<>();
    private TranslatorSpec(Builder builder) {
        this.toModelSpec = builder.toModelSpec;
        this.fromModelSpec = builder.fromModelSpec;
    }

    protected List<TranslatorSpecEntry> getToModelSpec() {
       return toModelSpec;
    }

    protected List<TranslatorSpecEntry> getFromModelSpec() {
        return fromModelSpec;
    }

    public static class Builder {
        private String modelDefaultPath;
        private String aceDefaultPath;
        private List<TranslatorSpecEntry> toModelSpec = new ArrayList<>();
        private List<TranslatorSpecEntry> fromModelSpec = new ArrayList<>();
        // Model Default Path (can be overridden in key)

        public Builder() {}

        public Builder modelDefaultPath(String path) {
            this.modelDefaultPath = path;
            return this;
        }
        // ACE Default Path (can be overridden in key)
        public Builder aceDefaultPath(String path) {
            this.aceDefaultPath = path;
            return this;
        }
        // Bidirectional mapping (cannot include a function here)
        public Builder map(String modelKey, String aceKey, String modelUnit) {
            try {
                TranslatorSpecEntry entry = new TranslatorSpecEntry(modelKey, aceKey, modelUnit, null, ":");
                this.toModelSpec.add(entry);
                this.fromModelSpec.add(entry);
            } catch (IllegalArgumentException ex) {
                System.out.printf("Cannot create a map TranslatorSpecEntry for modelKey: %s and aceKey: %s.%n%s%n", modelKey, aceKey, ex.getMessage());
            }
            return this;
        }
        // Mapping from the model to ACE
        public Builder fromModel(String modelKey, String aceKey, String modelUnit) {
            return addEntry(TranslationDirection.FROM_MODEL, modelKey, aceKey, modelUnit, null, ":");
        }
        public Builder fromModel(String modelKeys, String aceKeys, Function<String, String> fun) {
            return addEntry(TranslationDirection.FROM_MODEL, modelKeys, aceKeys, null, fun, ":");
        }
        public Builder fromModel(String modelKeys, String aceKeys, Function<String, String> fun, String keyDelim) {
            return addEntry(TranslationDirection.FROM_MODEL, modelKeys, aceKeys, null, fun, keyDelim);
        }

        private Builder addEntry(TranslationDirection dir, String modelKeys, String aceKeys, String modelUnit, Function<String, String> fun, String keyDelim) {
            try {
                switch(dir) {
                    case FROM_MODEL:
                        this.fromModelSpec.add(new TranslatorSpecEntry(modelKeys, aceKeys, modelUnit, fun, keyDelim));
                        break;
                    case TO_MODEL:
                        this.toModelSpec.add(new TranslatorSpecEntry(modelKeys, aceKeys, modelUnit, fun, keyDelim));
                        break;
                }
            } catch (IllegalArgumentException ex) {
                System.out.printf("Cannot create a TranslatorSpecEntry for modelKeys: %s and aceKeys: %s.%n%s%n", modelKeys, aceKeys, ex.getMessage());
            }
            return this;
        }
        //Mapping from ACE to the model
        public Builder toModel(String modelKey, String aceKey, String modelUnit) {
            return addEntry(TranslationDirection.TO_MODEL, modelKey, aceKey, modelUnit, null, ":");
        }
        public Builder toModel(String modelKeys, String aceKeys, Function<String, String> fun) {
            return addEntry(TranslationDirection.TO_MODEL, modelKeys, aceKeys, null, fun, ":");
        }
        public Builder toModel(String modelKeys, String aceKeys, Function<String, String> fun, String keyDelim) {
            return addEntry(TranslationDirection.TO_MODEL, modelKeys, aceKeys, null, fun, keyDelim);
        }
        public TranslatorSpec build() {
            for (TranslatorSpecEntry e : fromModelSpec) {
                e.updateModelPath(this.modelDefaultPath);
            }
            for (TranslatorSpecEntry e: toModelSpec) {
                e.updateAcePath(this.aceDefaultPath);
            }
            return new TranslatorSpec(this);
        }
    } 
}
