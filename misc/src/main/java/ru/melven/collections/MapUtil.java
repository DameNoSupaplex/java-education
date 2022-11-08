package ru.melven.collections;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MapUtil {

    private String dumpParams(Map<String, String[]> params) {
        return "{" + params.entrySet().stream().map(entry -> {
            String key = entry.getKey();
            String[] value = entry.getValue();
            if (value == null || value.length == 0) {
                return key;
            } else if (value.length == 1) {
                return key + "=" + value[0];
            } else {
                return key + "[" + String.join(",", value) + "]";
            }
        }).collect(Collectors.joining(",")) + "}";
    }

    private String dumpParams2(Map<String, String[]> params) {
        StringBuilder sb = new StringBuilder();
        params.forEach((key, values) -> {
            sb.append(",");
            if (values == null || values.length == 0) {
                sb.append(key);
            } else if (values.length == 1) {
                sb.append(key).append("=").append(values[0]);
            } else {
                sb.append(key).append("=").append("[").append(values[0]);
                Arrays.stream(values)
                        .skip(1)
                        .forEach(val -> sb.append(",").append(val));
                sb.append("]");
            }
        });
        if (sb.length() > 0) {
            sb.replace(0, 1, "{");
        } else {
            sb.append("{");
        }
        sb.append("}");
        return sb.toString();
    }
}
