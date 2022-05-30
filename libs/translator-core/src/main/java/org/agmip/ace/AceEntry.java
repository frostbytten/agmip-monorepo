package org.agmip.ace;

import org.agmip.icasa.IEntry;
import org.agmip.icasa.IcasaEntry;

import java.util.Objects;

public class AceEntry implements IEntry {
    private String name;
    private String query;
    private String unit;
    private boolean calculateHash;
    private String bucket;
    private String path;
    private String eventType;

    public AceEntry(IcasaEntry base) {
        if (Objects.isNull(base)) return;
        name = base.getName();
        query = base.getQuery();
        unit = base.getUnit();
        calculateHash = base.includeInHashCalculation();
        String[] pathParts = getPathFromOrder(base.getGroupOrder()).split("[:$]");
        if (pathParts.length == 2  || pathParts.length == 3) {
            bucket = pathParts[0];
            path = pathParts[1];
            if (pathParts.length == 3) {
                eventType = pathParts[2];
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public String getUnit() {
        return unit;
    }

    public boolean calculateHash() {
        return calculateHash;
    }

    public String getBucket() {
        return bucket;
    }

    public String getPath() {
        return path;
    }

    public String getEventType() {
        return eventType;
    }

    private String getPathFromOrder(int order) {
        if ((order >= 1011 && order <= 1081)
                || order == 2011
                || order == 2031
                || order == 2121
                || order == 2071
                || order == 2081
                || order == 2091
                || order == 2211) {
            // Global bucket
            return "experiments:/";
        } else if ((order >= 5001 && order <= 5013) || order == 5041 || order == 5046) {
            // Weather Global bucket
            return "weathers:/";
        } else if (order == 5052) {
            // Weather Daily data
            return "weathers:/dailyWeather";
        } else if ((order >= 4001 && order <= 4031) || (order >= 4041 && order <= 4042) || order == 4051) {
            // Soil Global
            return "soils:/";
        } else if (order == 4052) {
            // Soil Layer data
            return "soils:/soilLayer";
        } else if (order == 2051) {
            // Initial Conditions
            return "experiments:/initial_conditions";
        } else if (order == 2052) {
            // Initial Conditions soil layer data
            return "experiments:/initial_conditions/soilLayer";
        } else if (order == 2021 || order == 2061) {
            // Events - planting
            return "experiments:/management/events$planting";
        } else if (order == 2072) {
            // Events - irrigation
            return "experiments:/management/events$irrigation";
        } else if (order == 2073) {
            // Events - auto-irrigation
            return "experiments:/management/events$auto_irrig";
        } else if (order == 2082) {
            // Events - fertilizer
            return "experiments:/management/events$fertilizer";
        } else if (order == 2122) {
            // Events - tillage
            return "experiments:/management/events$tillage";
        } else if (order == 2141 || order == 2142) {
            // Events - harvest
            return "experiments:/management/events$harvest";
        } else if (order == 2092) {
            // Events - organic material
            return "experiments:/management/events$organic_matter";
        } else if (order == 2111 || order == 2112) {
            // Events - chemical
            return "experiments:/management/events$chemicals";
        } else if (order == 2101) {
            // Events - mulch
            return "experiments:/management/events$mulch_add";
        } else if (order == 2102) {
            // Events - mulch
            return "experiments:/management/events$mulch_remove";
        } else if (order >= 2502 && order <= 2510) {
            // Observed summary data
            return "experiments:/observed";
        } else if (order >= 2511 && order <= 2599) {
            // Observed time series data
            return "experiments:/observed/timeSeries";
        }
        return "";
    }
}
