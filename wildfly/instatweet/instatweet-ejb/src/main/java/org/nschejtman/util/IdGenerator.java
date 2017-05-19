package org.nschejtman.util;

import java.util.HashMap;
import java.util.Map;

public class IdGenerator {
    private static Map<String, Long> counters = new HashMap<String, Long>();

    public static long next(String counterName) {
        if (counters.containsKey(counterName)) {
            final Long nextCount = counters.get(counterName);
            counters.put(counterName, nextCount + 1);
            return nextCount;
        } else {
            counters.put(counterName, 0L);
            return 0L;
        }
    }
}