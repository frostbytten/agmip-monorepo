package org.agmip.icasa;

import org.agmip.icasa.loaders.csv.IcasaCsvResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public enum IcasaLookup {
    INSTANCE;

    private final static Logger LOG = LoggerFactory.getLogger(IcasaLookup.class);
    private IcasaEntryImmutableCollection entries = null;
    private IcasaCodeImmutableCollection codes = null;
    private IcasaLookupState state = IcasaLookupState.UNINITIALIZED;


    IcasaLookup() {}

    public void init() {
        if (state == IcasaLookupState.UNINITIALIZED) {
            try {
                LOG.info("Initializing lookup");
                entries = IcasaCsvResourceLoader.loadEntries();
                codes = IcasaCsvResourceLoader.loadCodes();
                state = IcasaLookupState.INITIALIZED;
            } catch (IOException ex) {
                LOG.error("Error initializing the ICASA lookup", ex);
                state = IcasaLookupState.FAILED;
            }
        }
    }

    public int lookupSize() {
        if (state != IcasaLookupState.INITIALIZED) {
            return -1;
        } else {
            return entries.variableLookupSize();
        }
    }

    public int codeSize() {
        if (state != IcasaLookupState.INITIALIZED) {
            return -1;
        } else {
            return codes.codeLookupSize();
        }
    }

    public int discreteCodeSize() {
        if (state != IcasaLookupState.INITIALIZED) {
            return -1;
        } else {
            return codes.codeMappingSize();
        }
    }

    public IcasaEntry lookupVariable(String key) {
        if (state == IcasaLookupState.UNINITIALIZED) {
            init();
        }
        if (state == IcasaLookupState.INITIALIZED) {
            return entries.lookupByVariable(key);
        }
        return null;
    }

    public IcasaEntry lookupQuery(String key) {
        if (state == IcasaLookupState.UNINITIALIZED) {
            init();
        }
        if (state == IcasaLookupState.INITIALIZED) {
            return entries.lookupByQuery(key);
        }
        return null;
    }

    public IcasaLookupState getState() {
        return state;
    }

    public IcasaCode lookupCode(String query, String code) {
        if (state == IcasaLookupState.UNINITIALIZED) {
            init();
        }
        if (state == IcasaLookupState.INITIALIZED) {
            return codes.lookupCode(query, code);
        }
        return null;
    }
}
