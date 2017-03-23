package com.bskyb.internettv;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class InfraScale {

    private Executor executor = Executors.newSingleThreadExecutor();

    private Map<String,String> statusMap = new LinkedHashMap<>();

    public InfraScale() {
        IntStream.range(1, 10)
                .forEach(index -> statusMap.put("extra-instance-"+index, "DOWN"));
    }

    public String start() {
        executor.execute(new ScaleTask(statusMap));
        return "STARTED";
    }

    public String stop() {
        return "STOPPED";
    }

    public synchronized Map<String,String> getStatus() throws Exception {
        return statusMap;
    }


    private static class ScaleTask implements Runnable {

        private final Map<String, String> scaleMap;

        private ScaleTask(Map<String,String> scaleMap) {
            this.scaleMap = scaleMap;
        }

        @Override
        public void run() {
            boolean keepGoing = true;
            do {
                try {
                    System.out.print("### Scaling up...");
                    Thread.sleep(40000);
                    Optional<Map.Entry<String, String>> entry = scaleMap.entrySet()
                            .stream()
                            .filter(entry1 -> entry1.getValue().equals("DOWN"))
                            .findFirst();
                    if (!entry.isPresent()) {
                        keepGoing = false;
                        System.out.println("\n\n### ALL INSTANCES UP");
                    } else {
                        scaleMap.put(entry.get().getKey(), "UP");
                        System.out.println(" " + entry.get().getKey() + " SCALED");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(keepGoing);
        }
    }
}
