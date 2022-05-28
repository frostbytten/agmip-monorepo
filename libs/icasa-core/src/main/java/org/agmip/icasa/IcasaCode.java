package org.agmip.icasa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class IcasaCode {
    private static final Logger LOG = LoggerFactory.getLogger(IcasaCode.class);
    private final String query;
    private final String code;
    private final String desc;
    private final Map<String, String> extra;

    private IcasaCode(String query, String code, String desc, Map<String, String> extra) {
        this.query = query;
        this.code = code;
        this.desc = desc;
        this.extra = extra;
    }

    public String getQuery() {
        return query;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return desc;
    }

    public Set<String> getExtraKeys() {
        return extra.keySet();
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public String getExtraValue(String key, String defaultValue) {
        return extra.getOrDefault(key, defaultValue);
    }

    public String getExtraValue(String key) {
        return getExtraValue(key, null);
    }
    public boolean hasExtra() {
        return extra.size() > 0;
    }

    public static class Builder {
        private String query;
        private String code;
        private String desc;
        private final Map<String, String> extra = new HashMap<>();

        public Builder query(String query) {
            this.query = query;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder addExtra(String key, String value){
            if (extra.containsKey(key)) {
                LOG.warn("Could not add duplicate key: {} with value {}", key, value);
            } else {
                if (Objects.nonNull(value) && !value.isBlank()) extra.put(key, value);
            }
            return this;
        }

        public IcasaCode build() {
            if (Objects.nonNull(query) && !query.isBlank() &&
                    Objects.nonNull(code) && !code.isBlank() &&
                    Objects.nonNull(desc) && !desc.isBlank()) {
                return new IcasaCode(query, code, desc, extra);
            } else {
                LOG.warn("Could not add IcasaCode with the following parameters: Q: {}, C:{}, D:{}", query, code, desc);
                return null;
            }
        }
    }
}
