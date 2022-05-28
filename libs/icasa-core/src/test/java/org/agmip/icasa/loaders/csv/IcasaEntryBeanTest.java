package org.agmip.icasa.loaders.csv;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.junit.jupiter.api.Test;

import java.io.IOError;
import java.io.StringReader;
import java.util.List;

import static org.agmip.icasa.IcasaSampleData.*;
import static org.junit.jupiter.api.Assertions.*;

public class IcasaEntryBeanTest {

    @Test
    public void cleanDataIsCorrectlyImported() {
        StringReader sr = new StringReader(cleanCorrectLines);
        List<IcasaEntryBean> data = new CsvToBeanBuilder<IcasaEntryBean>(sr)
                .withType(IcasaEntryBean.class)
                .build()
                .parse();
        sr.close();
        assertEquals(1, data.size());
        assertEquals("CRID", data.get(0).getQuery());
    }

    @Test
    public void extraDataIsCorrectlyIgnored() {
        StringReader sr = new StringReader(extraCorrectLines);
        List<IcasaEntryBean> data = new CsvToBeanBuilder<IcasaEntryBean>(sr)
                .withType(IcasaEntryBean.class)
                .build()
                .parse();
        sr.close();
        assertEquals(2, data.size());
        assertEquals("CUL_ID", data.get(1).getQuery());
    }

    @Test
    public void errorOnMissingRequired() {
        System.out.println(missingRequired);
        StringReader sr = new StringReader(missingRequired);
        assertThrows(RuntimeException.class, () -> {
            List<IcasaEntryBean> data = new CsvToBeanBuilder<IcasaEntryBean>(sr)
                    .withType(IcasaEntryBean.class)
                    .build()
                    .parse();
        });
        sr.close();
    }

    @Test
    public void outOfOrderWorks() {
        StringReader sr = new StringReader(outOfOrder);
        List<IcasaEntryBean> data = new CsvToBeanBuilder<IcasaEntryBean>(sr)
                .withType(IcasaEntryBean.class)
                .build()
                .parse();
        sr.close();
        assertEquals(1, data.size());
        assertEquals("CRID", data.get(0).getQuery());
    }

    @Test
    public void booleanDefaultWorks() {
        StringReader sr = new StringReader(booleanDefault);
        List<IcasaEntryBean> data = new CsvToBeanBuilder<IcasaEntryBean>(sr)
                .withType(IcasaEntryBean.class)
                .build()
                .parse();
        sr.close();
        assertEquals(1, data.size());
        assertEquals("CRID", data.get(0).getQuery());
        assertFalse(data.get(0).getHashCalc());
    }
}
