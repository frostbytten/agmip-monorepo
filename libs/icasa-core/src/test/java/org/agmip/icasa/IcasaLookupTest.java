package org.agmip.icasa;

import org.agmip.icasa.loaders.csv.IcasaCsvResourceLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class IcasaLookupTest {
    private static IcasaEntryImmutableCollection entries;
    private static IcasaCodeImmutableCollection codes;
    private static final Logger LOG = LoggerFactory.getLogger(IcasaLookupTest.class);

    @BeforeAll
    public static void setup() {
        try {
            entries = IcasaCsvResourceLoader.loadEntries();
            codes = IcasaCsvResourceLoader.loadCodes();
        } catch (IOException ex) {
            LOG.error("Setup error", ex);
        }
    }
    @Test
    public void icasaLookupVariableWorks() {
        IcasaEntry e = (IcasaEntry) IcasaLookup.lookupVariable("crop_ident_ICASA", entries);
        assertNotNull(e);
        assertEquals("CRID", e.getQuery());
    }

    @Test
    public void icasaLookupByQueryWorks() {
        IcasaEntry e = (IcasaEntry) IcasaLookup.lookupQuery("CRID", entries);
        assertNotNull(e);
        assertEquals("crop_ident_ICASA", e.getName());
    }

    @Test
    public void icasaLookupCodeWorks() {
        IcasaCode c = IcasaLookup.lookupCode("CRID", "MAZ", codes);
        assertNotNull(c);
        assertEquals("Maize", c.getDescription());
        assertTrue(c.hasExtra());
    }

    @Test
    public void icasaLookupCodeExtra() {
        IcasaCode c = IcasaLookup.lookupCode("CRID", "MAZ", codes);
        IcasaCode c2 = IcasaLookup.lookupCode("FACTORS", "FACE", codes);
        assertNotNull(c);
        assertNotNull(c2);
        assertTrue(c.hasExtra());
        assertFalse(c2.hasExtra());
        Map<String, String> extras = c.getExtra();
        assertEquals("Cereal", extras.getOrDefault("Major_use", "BROKEN"));
        assertEquals("MZ", extras.getOrDefault("DSSAT_code", "BROKEN"));
    }

    @Test
    public void icasaLookupPathParts() {
        IcasaEntry e = (IcasaEntry) IcasaLookup.lookupQuery("CRID", entries);
        assertNotNull(e);
        assertEquals("experiments", e.getIcasaPathPrefix());
        assertEquals("planting", e.getIcasaPathSuffix());
        assertEquals("/management/events", e.getIcasaPathPointer());
    }
}
