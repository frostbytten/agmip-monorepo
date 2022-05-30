package org.agmip.ace;

import org.agmip.icasa.IEntry;
import org.agmip.icasa.IcasaCode;
import org.agmip.icasa.IcasaCodeImmutableCollection;
import org.agmip.icasa.IcasaEntry;
import org.agmip.icasa.IcasaEntryImmutableCollection;
import org.agmip.icasa.IcasaLookup;
import org.agmip.icasa.IcasaLookupState;
import org.agmip.icasa.loaders.csv.IcasaCsvResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public enum AcePathfinder {
    INSTANCE;

    private static final Logger LOG = LoggerFactory.getLogger(AcePathfinder.class);
    private IcasaEntryImmutableCollection entries = null;
    private IcasaCodeImmutableCollection codes = null;

    private IcasaLookupState state = IcasaLookupState.UNINITIALIZED;

    public void init() {
        if (state.equals(IcasaLookupState.UNINITIALIZED)) {
            try {
                List<IEntry> backingStore = IcasaCsvResourceLoader.loadEntries().getBackingStore();
                IcasaEntryImmutableCollection.Builder builder = new IcasaEntryImmutableCollection.Builder();
                for (IEntry entry : backingStore) {
                    builder.addEntry(new AceEntry((IcasaEntry) entry));
                }
                entries = builder.build();
                codes = IcasaCsvResourceLoader.loadCodes();
                state = IcasaLookupState.INITIALIZED;
            } catch (IOException ex) {
                LOG.error("Could not initialize the AcePathfinder.", ex);
                state = IcasaLookupState.FAILED;
            }
        }
    }

    public boolean isInitialized() {
        if (state.equals(IcasaLookupState.UNINITIALIZED)) {
            init();
        }
        return state.equals(IcasaLookupState.INITIALIZED);
    }
    public AceEntry lookup(String query) {
        if (isInitialized())
            return (AceEntry) IcasaLookup.lookupQuery(query.toUpperCase(Locale.ENGLISH), entries);
        return null;
    }

    public AceEntry lookupByVariable(String variable) {
        if (isInitialized())
            return (AceEntry) IcasaLookup.lookupVariable(variable, entries);
        return null;
    }

    public IcasaCode lookupCode(String query, String code) {
        if (isInitialized())
            return IcasaLookup.lookupCode(query.toUpperCase(Locale.ENGLISH), code, codes);
        return null;
    }

    public IcasaCode lookupCodeByVariable(String variable, String code) {
        if (isInitialized()) {
            IcasaEntry e = (IcasaEntry) IcasaLookup.lookupVariable(variable, entries);
            return IcasaLookup.lookupCode(e.getQuery(), code, codes);
        } else {
            return null;
        }
    }
}
