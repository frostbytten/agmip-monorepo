package org.agmip.icasa;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IcasaEntryImmutableCollectionTest {
    @Test
    public void blankCollectionWorks() {
        IcasaEntryImmutableCollection blank = new IcasaEntryImmutableCollection.Builder().build();
        assertEquals(0, blank.queryLookupSize());
        assertEquals(0, blank.variableLookupSize());
        assertNull(blank.lookupByQuery("ANYTHING"));
        assertNull(blank.lookupByVariable("CRID"));
    }

    @Test
    public void addMultipleItemsToTheCollection() {
        IcasaEntryImmutableCollection.Builder builder = new IcasaEntryImmutableCollection.Builder();
        createTwoEntries().forEach(builder::addEntry);
        assertEquals(2, builder.size());
    }

    @Test
    public void retrieveValuesFromMaps() {
        IcasaEntryImmutableCollection.Builder builder = new IcasaEntryImmutableCollection.Builder();
        createTwoEntries().forEach(builder::addEntry);
        IcasaEntryImmutableCollection coll = builder.build();
        assertEquals("PDATE", coll.lookupByVariable("planting_date").getQuery());
        assertEquals("Crop (or weed) species identifier", coll.lookupByQuery("CRID").getDescription());
    }

    private List<IcasaEntry> createTwoEntries() {
        List<IcasaEntry> entries = new ArrayList<>();
        IcasaEntry e1 = new IcasaEntry.Builder()
                .name("crop_ident_ICASA")
                .query("CRID")
                .desc("Crop (or weed) species identifier")
                .unit("code")
                .order(2021)
                .includeInHashCalculate(true)
                .build();
        IcasaEntry e2 = new IcasaEntry.Builder()
                .name("planting_date")
                .query("PDATE")
                .desc("Planting or sowing date")
                .unit("date")
                .order(2061)
                .includeInHashCalculate(true)
                .build();
        entries.add(e1);
        entries.add(e2);
        return entries;
    }
}
