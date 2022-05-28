package org.agmip.icasa.loaders.csv;

import com.opencsv.bean.CsvToBeanBuilder;
import org.agmip.icasa.IcasaCode;
import org.agmip.icasa.IcasaCodeImmutableCollection;
import org.agmip.icasa.IcasaEntry;
import org.agmip.icasa.IcasaEntryImmutableCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class IcasaCsvResourceLoader {
    public static final Logger LOG = LoggerFactory.getLogger(IcasaCsvResourceLoader.class);

    public static IcasaEntryImmutableCollection loadEntries() throws IOException {
        List<String> entryFiles = Stream.of("metadata.csv", "management_info.csv", "soils_data.csv", "weather_data.csv", "measured_data.csv").map(x -> "/icasa-mvl/" + x).toList();
        IcasaEntryImmutableCollection.Builder builder = new IcasaEntryImmutableCollection.Builder();
        for (String file : entryFiles) {
            loadEntriesFromEmbeddedCsv(file, builder);
        }
        return builder.build();
    }

    public static IcasaCodeImmutableCollection loadCodes() throws IOException {
        List<String> codeFiles = Stream.of("metadata_codes.csv", "crop_codes.csv", "management_codes.csv", "pest_codes.csv").map(x -> "/icasa-mvl/" + x).toList();
        IcasaCodeImmutableCollection.Builder builder = new IcasaCodeImmutableCollection.Builder();
        for (String file: codeFiles) {
            loadCodesFromEmbeddedCsv(file, builder);
        }
        return builder.build();
    }

    private static void loadEntriesFromEmbeddedCsv(String resourceId, IcasaEntryImmutableCollection.Builder builder) throws IOException {
        InputStream resStream;
        List<IcasaEntryBean> resEntries = null;
        resStream = IcasaCsvResourceLoader.class.getResourceAsStream(resourceId);
        LOG.info("Loading entries from {}", resourceId);
        if (Objects.nonNull(resStream)) {
            InputStreamReader reader = new InputStreamReader(resStream);
            resEntries = new CsvToBeanBuilder<IcasaEntryBean>(reader)
                    .withIgnoreEmptyLine(true)
                    .withType(IcasaEntryBean.class)
                    .build().parse();
            reader.close();
            resStream.close();
        } else {
            LOG.error("Unable to load {}", resourceId);
        }
        if (Objects.isNull(resEntries)) return;
        for (IcasaEntryBean e : resEntries) {
            if (e.getFilter() != -2) {
                builder.addEntry(new IcasaEntry.Builder()
                        .name(e.getName())
                        .query(e.getQuery())
                        .desc(e.getDesc())
                        .unit(e.getUnit())
                        .order(e.getOrder())
                        .includeInHashCalculate(e.getHashCalc())
                        .build());
            }
        }
    }

    private static void loadCodesFromEmbeddedCsv(String resourceId, IcasaCodeImmutableCollection.Builder builder) throws IOException {
        InputStream resStream;
        List<IcasaCodeBean> resCodes = null;
        String profile = resourceId.split("[/.]")[2];
        LOG.info("Loading codes from {}", resourceId);
        resStream = IcasaCsvResourceLoader.class.getResourceAsStream(resourceId);
        if (Objects.nonNull(resStream)) {
            InputStreamReader reader = new InputStreamReader(resStream);
            resCodes = (new CsvToBeanBuilder<IcasaCodeBean>(reader)
                    .withProfile(profile)
                    .withIgnoreEmptyLine(true)
                    .withType(IcasaCodeBean.class)
                    .build().parse());
            reader.close();
            resStream.close();
        } else {
            LOG.error("Unable to load {}", resourceId);
        }
        if (Objects.isNull(resCodes)) {
            LOG.warn("Did not load any codes from {}", resourceId);
            return;
        }
        for (IcasaCodeBean b : resCodes) {
            if (Objects.nonNull(b.getQuery()) && ! b.getQuery().isBlank() &&
                    Objects.nonNull(b.getCode()) &&
                    Objects.nonNull(b.getDescription())) {
                for (String query : b.getQuery().split(",")) {
                    IcasaCode code = new IcasaCode.Builder()
                            .query(query.trim())
                            .code(b.getCode())
                            .desc(b.getDescription())
                            .addExtra("Latin_name", b.getLatin())
                            .addExtra("Major_use", b.getUse())
                            .addExtra("Other_uses", b.getOther())
                            .addExtra("DSSAT_code", b.getDssat())
                            .addExtra("APSIM_code", b.getApsim())
                            .addExtra("AgTrials_code", b.getAgtrials())
                            .build();
                    if (Objects.nonNull(code)) builder.addCode(code);
                }
            }
        }
    }
}
