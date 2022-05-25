package org.agmip.ace.internal;

public class IcasaEntry {
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
        private StringBuilder _path = new StringBuilder();
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

        public Builder addPathSegment(String segment) {
            this._path.append("/");
            this._path.append(segment);
            return this;        
        }

        public Builder order(int order) {
            this._order = order;
            return this;
        }

        public Builder includeInHashCalculate(boolean include) {
            this._calc = include;
            return this;
        }
        
        public IcasaEntry build() {
            return new IcasaEntry(_name, _query, _desc, _unit, _path.toString(), _order, _calc);
        }
    }
}
