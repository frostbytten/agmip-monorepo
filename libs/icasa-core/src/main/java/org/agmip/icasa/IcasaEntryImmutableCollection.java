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
    private final List<IEntry> store;
    private final Map<String, Integer> variableLookup = new HashMap<>();
    private final Map<String, Integer> queryLookup = new HashMap<>();

    private IcasaEntryImmutableCollection(List<IEntry> collection, boolean overwrite) {
        if (Objects.nonNull(collection)) {
            store = collection;
            for (int i=0; i < store.size(); i++) {
                IEntry e = store.get(i);
                if (Objects.nonNull(e.getName())) {
                    if (overwrite || !variableLookup.containsKey(e.getName())) {
                        variableLookup.put(e.getName(), i);
                    } else {
                        LOG.warn("Variable {} already found, not overwriting.", e.getName());
                    }
                }
                if (Objects.nonNull(e.getQuery())) {
                    if (overwrite || !queryLookup.containsKey(e.getQuery())) {
                        queryLookup.put(e.getQuery(), i);
                    } else {
                        LOG.warn("Query {} already found, not overwriting.", e.getQuery());
                    }
                }
            }
        } else {
            store = new ArrayList<>();
        }
    }

    public int variableLookupSize() {
        return variableLookup.size();
    }

    public int queryLookupSize() {
        return queryLookup.size();
    }

    public IEntry lookupByVariable(String key) {
        return lookupInMap(key, variableLookup);
    }

    public IEntry lookupByQuery(String key) {
        return lookupInMap(key, queryLookup);
    }

    private IEntry lookupInMap(String key, Map<String, Integer> map) {
        Integer index = map.getOrDefault(key, null);
        if (Objects.isNull(index) || index < 0 || index > store.size()) return null;
        return store.get(index);
    }

    public List<IEntry> getBackingStore() {
        return store;
    }

    public static class Builder {
        private final List<IEntry> collection = new ArrayList<>();
        private boolean overwrite = false;

        public Builder addEntry(IEntry entry) {
            collection.add(entry);
            return this;
        }

        public Builder removeEntry(IEntry entry) {
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
