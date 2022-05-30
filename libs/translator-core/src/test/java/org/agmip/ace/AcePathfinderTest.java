package org.agmip.ace;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AcePathfinderTest {
    @Test
    public void lookupTest() {
        AceEntry e = AcePathfinder.INSTANCE.lookup("CRID");
        assertNotNull(e);
        assertEquals("experiments", e.getBucket());
        assertEquals("planting", e.getEventType());
        assertEquals("/management/events", e.getPath());
    }
}
