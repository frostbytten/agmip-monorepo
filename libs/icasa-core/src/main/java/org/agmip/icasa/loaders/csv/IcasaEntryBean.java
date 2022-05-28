package org.agmip.icasa.loaders.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.processor.PreAssignmentProcessor;
import com.opencsv.bean.processor.StringProcessor;

import java.util.Objects;

import static org.agmip.icasa.loaders.csv.IcasaCsvHeaders.*;

public class IcasaEntryBean {
    @CsvBindByName(column = NAME, required = true)
    private String name;

    @CsvBindByName(column = QUERY, required = true)
    private String query;

    @CsvBindByName(column = DESC)
    private String desc;

    @CsvBindByName(column = UNIT)
    private String unit;

    @CsvBindByName(column = ORDER)
    private Integer order;

    @PreAssignmentProcessor(processor = ConvertBinaryToBoolean.class, paramString = "0")
    @CsvBindByName(column = CALC)
    private boolean calc;

    @PreAssignmentProcessor(processor = ConvertNullToDefaultString.class, paramString = "0")
    @CsvBindByName(column = FILTER)
    private Integer filter;

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public String getDesc() {
        return desc;
    }

    public String getUnit() {
        return unit;
    }

    public int getOrder() {
        return order;
    }

    public int getFilter() {
        return filter;
    }

    public boolean getHashCalc() {
        return calc;
    }

    public static class ConvertBinaryToBoolean implements StringProcessor {
        String defaultValue;

        @Override
        public String processString(String value) {
            if (value == null || value.isEmpty()) {
                value = defaultValue;
            }
            if (value.trim().equals("1")) {
                return "true";
            } else {
                return "false";
            }
        }

        @Override
        public void setParameterString(String value) {
            defaultValue = value;
        }
    }

    public static class ConvertNullToDefaultString implements StringProcessor {
        String defaultValue;

        @Override
        public String processString(String value) {
            if (Objects.isNull(value) || value.isBlank()) {
                return defaultValue;
            } else {
                return value;
            }
        }

        @Override
        public void setParameterString(String value) {
            defaultValue = value;
        }
    }
}