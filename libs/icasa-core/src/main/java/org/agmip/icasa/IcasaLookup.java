package org.agmip.icasa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IcasaLookup {
    private final static Logger LOG = LoggerFactory.getLogger(IcasaLookup.class);
    private IcasaLookup(){}

    public static int lookupSize(IcasaEntryImmutableCollection entries) {
        return entries.variableLookupSize();
    }

    public static int codeSize(IcasaCodeImmutableCollection codes) {
        return codes.codeLookupSize();
    }

    public static int discreteCodeSize(IcasaCodeImmutableCollection codes) {
        return codes.codeMappingSize();
    }

    public static IEntry lookupVariable(String key, IcasaEntryImmutableCollection entries) {
            return entries.lookupByVariable(key);
    }

    public static IEntry lookupQuery(String key, IcasaEntryImmutableCollection entries) {
        return entries.lookupByQuery(key);
    }

    public static IcasaCode lookupCode(String query, String code, IcasaCodeImmutableCollection codes) {
        return codes.lookupCode(query, code);
    }
}
