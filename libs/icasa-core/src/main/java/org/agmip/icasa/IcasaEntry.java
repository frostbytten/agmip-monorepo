package org.agmip.icasa;

import java.util.Objects;

public class IcasaEntry implements IEntry {
    private final String name;
    private final String query;
    private final String desc;
    private final String unit;
    private final String icasaPath;
    private final int groupOrder;
    private final boolean hashCalc;

    private IcasaEntry(String name, String query, String desc, String unit, String icasaPath, int groupOrder, boolean hashCalc) {
        this.name = name;
        this.query = query;
        this.desc = desc;
        this.unit = unit;
        this.icasaPath = icasaPath;
        this.groupOrder = groupOrder;
        this.hashCalc = hashCalc;
    }

    public String getName() {
        return name;
    }

    public String getQuery() {
        return query;
    }

    public String getDescription() {
        return desc;
    }

    public String getUnit() {
        return unit;
    }

    public String getIcasaPath() {
        return icasaPath;
    }

    public String getIcasaPathPrefix() {
        if (Objects.nonNull(icasaPath)) {
           return icasaPath.split("[:/]")[0];
        } else {
            return null;
        }
    }

    public String getIcasaPathPointer() {
        if (Objects.nonNull(icasaPath)) {
            return icasaPath.split("[:$]")[1];
        } else {
            return null;
        }
    }

    public String getIcasaPathSuffix() {
        if (Objects.nonNull(icasaPath) && icasaPath.contains("$")) {
            String[] pathParts = icasaPath.split("[$]");
            return pathParts[1];
        } {
            return null;
        }
    }

    public int getGroupOrder() {
        return groupOrder;
    }

    public boolean includeInHashCalculation() {
        return hashCalc;
    }

    public static class Builder {
        private String _name;
        private String _query;
        private String _desc;
        private String _unit;
        private String _path;
        private int _order;
        private boolean _calc;

        public Builder name(String name) {
            this._name = name;
            return this;
        }

        public Builder query(String query) {
            this._query = query;
            return this;
        }

        public Builder desc(String desc) {
            this._desc = desc;
            return this;
        }

        public Builder unit(String unit) {
            this._unit = unit;
            return this;
        }

        public Builder order(int order) {
            this._order = order;
            this._path = getPathFromOrder(this._order);
            return this;
        }

        public Builder includeInHashCalculate(boolean include) {
            this._calc = include;
            return this;
        }

        public IcasaEntry build() {
            return new IcasaEntry(_name, _query, _desc, _unit, _path, _order, _calc);
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
            return null;
        }
    }
}
