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
        params = new HashMap<String, String>();
        while (tokenizer.hasMoreTokens()) {
            params.put(tokenizer.nextToken(), tokenizer.nextToken());
        }
    }

    public String getName() {
        return name;
    }

    public String getParam(String paramName) {
        return params.get(paramName);
    }
}
