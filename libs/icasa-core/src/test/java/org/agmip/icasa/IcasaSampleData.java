package org.agmip.icasa;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;

import java.io.StringWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.agmip.icasa.loaders.csv.IcasaCsvHeaders.*;

public class IcasaSampleData {
    public static final String cleanHeaders = new RowBuilder()
            .add(NAME)
            .add(QUERY)
            .add(DESC)
            .add(UNIT)
            .add(ORDER)
            .add(CALC)
            .add(FILTER)
            .build();

    public static final String outOfOrderHeaders = new RowBuilder()
            .add(ORDER)
            .add(NAME)
            .add(DESC)
            .add(QUERY)
            .add(UNIT)
            .add(CALC)
            .add(FILTER)
            .build();

    public static final String extraHeaders = new RowBuilder()
            .add(NAME)
            .add(QUERY)
            .add(DESC)
            .add(UNIT)
            .add(DATASET)
            .add(SUBSET)
            .add(GROUP)
            .add(SUBGROUP)
            .add(ORDER)
            .add(CALC)
            .add(FILTER)
            .build();
    public static final String cleanCorrectLines = new TableBuilder()
            .addRow(cleanHeaders)
            .addRow(new RowBuilder()
                    .add("crop_ident_ICASA")
                    .add("CRID")
                    .add("Crop (or weed) species identifier")
                    .add("code")
                    .add("2021")
                    .add("1")
                    .addRaw("")
                    .build())
            .build();

    public static final String missingRequired = new TableBuilder()
            .addRow(cleanHeaders)
            .addRow(new RowBuilder()
                    .add(null)
                    .add(null)
                    .add("Invalid line on purpose")
                    .add("")
                    .add("1234")
                    .add("100")
                    .add("")
                    .build())
            .build();

    public static final String extraCorrectLines = new TableBuilder()
            .addRow(extraHeaders)
            .addRow(new RowBuilder()
                    .add("crop_ident_ICASA")
                    .add("CRID")
                    .add("Crop (or weed) species identifier")
                    .add("code")
                    .add("EXPERIMENT")
                    .add("MANAGEMENT")
                    .add("GENOTYPES")
                    .add("")
                    .add("2021")
                    .add("1")
                    .addRaw("")
                    .build())
            .addRow(new RowBuilder()
                    .add("cultivar_identifier")
                    .add("CUL_ID")
                    .add("Cultivar, line or genotype identifier")
                    .add("")
                    .add("EXPERIMENT")
                    .add("MANAGEMENT")
                    .add("GENOTYPES")
                    .add("")
                    .add("2021")
                    .add("1")
                    .addRaw("")
                    .build())
            .build();
    public static final String outOfOrder = new TableBuilder()
            .addRow(outOfOrderHeaders)
            .addRow(new RowBuilder()
                    .add("2021")
                    .add("crop_ident_ICASA")
                    .add("Crop (or weed) species identifier")
                    .add("CRID")
                    .add("code")
                    .add("1")
                    .addRaw("")
                    .build())
            .build();

    public static final String booleanDefault = new TableBuilder()
            .addRow(cleanHeaders)
            .addRow(new RowBuilder()
                    .add("crop_ident_ICASA")
                    .add("CRID")
                    .add("Crop (or weed) species identifier")
                    .add("code")
                    .add("2021")
                    .add("1231")
                    .add("")
                    .build())
            .build();

    // NOTE This is an append-only builder.
    static class RowBuilder {
        private final List<String> cols = new ArrayList<>();

        public RowBuilder add(String col) {
            if (Objects.isNull(col)) {
                cols.add("");
            } else {
                cols.add("\"" + col + "\"");
            }
            return this;
        }

        public RowBuilder addRaw(String col) {
            cols.add(col);
            return this;
        }

        public String build() {
            return String.join(",", cols);
        }
    }

    static class TableBuilder {
        private final List<String> rows = new ArrayList<>();

        public TableBuilder addRow(String row) {
            rows.add(row);
            return this;
        }

        public String build() {
            return String.join("\n", rows);
        }
    }
}
