package org.agmip.translators.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranslatorSpecEntryTest {
    @Test
    public void updateFromModelPathWorks() {
        TranslatorSpecEntry e = new TranslatorSpecEntry("hello", "there", "mm", null, ":");
        e.updateModelPath("/batman");
        assertEquals(1, e.getModelKeys().size());
        assertEquals("/batman/hello", e.getModelKeys().get(0));
    }

    @Test
    public void updateToModelPathWorks() {
        TranslatorSpecEntry e = new TranslatorSpecEntry("hello", "there", "mm", null, ":");
        e.updateAcePath("/batman/");
        assertEquals(1, e.getAceKeys().size());
        assertEquals("/batman/there", e.getAceKeys().get(0));
    }

    @Test
    public void testKeySplitting() {
        TranslatorSpecEntry e = new TranslatorSpecEntry("hello", "you:there", "mm", null, ":");
        e.updateAcePath("/batman");
        assertEquals(1, e.getModelKeys().size());
        assertEquals(2, e.getAceKeys().size());
        assertEquals("/batman/there", e.getAceKeys().get(1));
    }

    @Test
    public void throwsOnManyToManyKeys() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new TranslatorSpecEntry("hello:hey", "you:there", "mm", null, ":");
        });
        assertEquals("Both modelKeys and aceKeys cannot contain multiple keys.", thrown.getMessage());
    }

    @Test
    public void simpleFunctionTest() {
        TranslatorSpecEntry e = new TranslatorSpecEntry("hello", "there", null, (s) -> {return s+" :)"; }, ":");
        assertEquals("gotham :)", e.getFun().apply("gotham"));
    }
}
