package org.agmip.icasa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IcasaCodeImmutableCollection {
    private static final Logger LOG = LoggerFactory.getLogger(IcasaCodeImmutableCollection.class);
    private final Map<String, IcasaCode> codeLookup = new HashMap<>();
    private final Map<String, List<IcasaCode>> codeMapping = new HashMap<>();

    private IcasaCodeImmutableCollection(List<IcasaCode> collection, boolean overwrite) {
        if (Objects.nonNull(collection)) {
            for (IcasaCode c: collection) {
                String key = c.getQuery() + ":" + c.getCode();
                if (overwrite || !codeLookup.containsKey(key)) {
                    codeLookup.put(key, c);
                } else {
                    LOG.warn("Query {} with Code {} already found, not overwriting.", c.getQuery(), c.getCode());
                }
                if (!codeMapping.containsKey(c.getQuery())) {
                    codeMapping.put(c.getQuery(), new ArrayList<>());
                }
                codeMapping.get(c.getQuery()).add(c);
            }
        }
    }

    public int codeLookupSize() {
        return codeLookup.size();
    }

    public int codeMappingSize() {
        return codeMapping.size();
    }

    public Map<String, List<IcasaCode>> getCodeMapping() {
        return codeMapping;
    }

    public IcasaCode lookupCode(String query, String key) {
        return codeLookup.getOrDefault(query+":"+key, null);
    }

    public static class Builder {
        private final List<IcasaCode> collection = new ArrayList<>();
        private boolean overwrite = false;

        public Builder addCode(IcasaCode code) {
            if (Objects.nonNull(code))
                collection.add(code);
            return this;
        }

        public Builder removeCode(IcasaCode code) {
            if (Objects.nonNull(code))
                collection.remove(code);
            return this;
        }

        public Builder allowOverwrite() {
            this.overwrite = true;
            return this;
        }

        public Builder preventOverwrite() {
            this.overwrite = false;
            return this;
        }

        public int size() {
            return collection.size();
        }

        public IcasaCodeImmutableCollection build() {
            return new IcasaCodeImmutableCollection(collection, overwrite);
        }
    }
}
