package org.agmip.icasa;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class IcasaLookupTest {
    @Test
    public void icasaLookupUserInit() {
        IcasaLookup.INSTANCE.init();
        assertTrue(IcasaLookup.INSTANCE.lookupSize() >= 0);
    }

    @Test
    public void icasaLookupLazyInit() {
        assertEquals(IcasaLookupState.UNINITIALIZED, IcasaLookup.INSTANCE.getState());
        IcasaEntry e = IcasaLookup.INSTANCE.lookupQuery("CRID");
        assertEquals(IcasaLookupState.INITIALIZED, IcasaLookup.INSTANCE.getState());
    }

    @Test
    public void icasaLookupVariableWorks() {
        IcasaEntry e = IcasaLookup.INSTANCE.lookupVariable("crop_ident_ICASA");
        assertNotNull(e);
        assertEquals("CRID", e.getQuery());
        String[] pathInfo = e.getIcasaPath().split("[:/]");
        assertEquals("experiments", pathInfo[0]);
    }

    @Test
    public void icasaLookupByQueryWorks() {
        IcasaEntry e = IcasaLookup.INSTANCE.lookupQuery("CRID");
        assertNotNull(e);
        assertEquals("crop_ident_ICASA", e.getName());
        assertEquals("experiments:/management/events$planting", e.getIcasaPath());
    }

    @Test
    public void icasaLookupCodeWorks() {
        IcasaCode c = IcasaLookup.INSTANCE.lookupCode("CRID", "MAZ");
        assertNotNull(c);
        assertEquals("Maize", c.getDescription());
        assertTrue(c.hasExtra());
    }

    @Test
    public void icasaLookupCodeExtra() {
        IcasaCode c = IcasaLookup.INSTANCE.lookupCode("CRID", "MAZ");
        IcasaCode c2 = IcasaLookup.INSTANCE.lookupCode("FACTORS", "FACE");
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
        IcasaEntry e = IcasaLookup.INSTANCE.lookupQuery("CRID");
        assertNotNull(e);
        assertEquals("experiments", e.getIcasaPathPrefix());
        assertEquals("planting", e.getIcasaPathSuffix());
        assertEquals("/management/events", e.getIcasaPathPointer());
    }
}
