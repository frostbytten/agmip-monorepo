package org.agmip.icasa.loaders.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByNames;

public class IcasaCodeBean {
    @CsvBindByName(column = "Code_display")
    private String query;

    @CsvBindByNames({
            @CsvBindByName(column = "Code"),
            @CsvBindByName(column = "Crop_code", profiles = "crop_codes"),
            @CsvBindByName(column = "Pest_code", profiles = "pest_codes")
    })
    private String code;

    @CsvBindByNames({
        @CsvBindByName(column = "Description"),
        @CsvBindByName(column = "Common_name", profiles = {"crop_codes", "pest_codes"})

    })
    private String desc;

    @CsvBindByName(column = "Latin_name")
    private String latin;

    @CsvBindByName(column = "Major_use")
    private String use;

    @CsvBindByName(column = "Other_uses")
    private String other;

    @CsvBindByName(column = "DSSAT_code")
    private String dssat;

    @CsvBindByName(column = "APSIM_code")
    private String apsim;

    @CsvBindByName(column = "AgTrials_code")
    private String agtrials;

    public String getQuery() {
        return query;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return desc;
    }

    public String getLatin() {
        return latin;
    }

    public String getOther() {
        return other;
    }

    public String getDssat() {
        return dssat;
    }

    public String getApsim() {
        return apsim;
    }

    public String getAgtrials() {
        return agtrials;
    }

    public String getUse() {
        return use;
    }
}