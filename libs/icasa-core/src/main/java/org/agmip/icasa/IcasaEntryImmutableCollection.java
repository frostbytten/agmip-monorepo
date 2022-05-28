package org.agmip.icasa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO Javadocs
public class IcasaEntryImmutableCollection {
    private static final Logger LOG = LoggerFactory.getLogger(IcasaEntryImmutableCollection.class);
    private final Map<String, IcasaEntry> variableLookup = new HashMap<>();
    private final Map<String, IcasaEntry> queryLookup = new HashMap<>();

    private IcasaEntryImmutableCollection(List<IcasaEntry> collection, boolean overwrite) {
        if (Objects.nonNull(collection)) {
            for (IcasaEntry e : collection) {
                if (Objects.nonNull(e.getName())) {
                    if (overwrite || !variableLookup.containsKey(e.getName())) {
                        variableLookup.put(e.getName(), e);
                    } else {
                        LOG.warn("Variable {} already found, not overwriting.", e.getName());
                    }
                }
                if (Objects.nonNull(e.getQuery())) {
                    if (overwrite || !queryLookup.containsKey(e.getQuery())) {
                        queryLookup.put(e.getQuery(), e);
                    } else {
                        LOG.warn("Query {} already found, not overwriting.", e.getQuery());
                    }
                }
            }
        }
    }

    public int variableLookupSize() {
        return variableLookup.size();
    }

    public int queryLookupSize() {
        return queryLookup.size();
    }

    public IcasaEntry lookupByVariable(String key) {
        return lookupInMap(key, variableLookup);
    }

    public IcasaEntry lookupByQuery(String key) {
        return lookupInMap(key, queryLookup);
    }

    private IcasaEntry lookupInMap(String key, Map<String, IcasaEntry> map) {
        return map.getOrDefault(key, null);
    }

    public static class Builder {
        private final List<IcasaEntry> collection = new ArrayList<>();
        private boolean overwrite = false;

        public Builder addEntry(IcasaEntry entry) {
            collection.add(entry);
            return this;
        }

        public Builder removeEntry(IcasaEntry entry) {
            collection.remove(entry);
            return this;
        }

        public Builder allowOverwrite() {
            overwrite = true;
            return this;
        }

        public Builder preventOverwrite() {
            overwrite = false;
            return this;
        }

        public IcasaEntryImmutableCollection build() {
            return new IcasaEntryImmutableCollection(collection, overwrite);
        }

        public int size() {
            return this.collection.size();
        }
    }
}
