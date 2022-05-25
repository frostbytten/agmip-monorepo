package org.agmip.translators.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TranslatorSpecTest {

    @Test
    public void addBidiTranslatorSpecEntry() {
        TranslatorSpec spec = new TranslatorSpec.Builder()
                .modelDefaultPath("/Stations")
                .aceDefaultPath("/weathers")
                .map("Elev", "elev", "m")
                .build();
        assertEquals(1, spec.getToModelSpec().size());
        assertEquals(1, spec.getFromModelSpec().size());
        assertEquals(spec.getToModelSpec().get(0).getModelKeys().get(0), spec.getFromModelSpec().get(0).getModelKeys().get(0));
        assertEquals(spec.getToModelSpec().get(0).getAceKeys().get(0), spec.getFromModelSpec().get(0).getAceKeys().get(0));
        assertEquals("/weathers/elev", spec.getToModelSpec().get(0).getAceKeys().get(0));
        assertEquals("/Stations/Elev", spec.getFromModelSpec().get(0).getModelKeys().get(0));
    }


    @Test
    public void addFromTranslatorSpecEntry() {
        TranslatorSpec spec = new TranslatorSpec.Builder()
                .modelDefaultPath("/Stations/Weather")
                .aceDefaultPath("/weathers/dailyWeather")
                .build();
    }
}
