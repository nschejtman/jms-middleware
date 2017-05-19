package com.nschejtman.client;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Command {
    private String name;
    private Map<String, String> params;

    Command(String line) {
        final StringTokenizer tokenizer = new StringTokenizer(line, " ");
        name = tokenizer.nextToken();
        params = new HashMap<>();
        int paramIdx = 1;
        while (tokenizer.hasMoreTokens()) {
            final String nextToken = tokenizer.nextToken();
            if (nextToken.charAt(0) == '-') {
                params.put(nextToken, tokenizer.nextToken());
            } else {
                params.put("-" + paramIdx, nextToken);
                paramIdx++;
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getParam(String... paramNames) {
        for (String paramName : paramNames) {
            final String result = params.get(paramName);
            if (result != null) return result;
        }
        return null;
    }
}
