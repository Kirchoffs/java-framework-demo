package org.syh.demo.flink.demo.water.source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

// For local environment, the number of task slots is the number of CPU cores by default.
// For Mac OS, we can get the number of CPU cores by running `sysctl -n hw.ncpu`.
public class CollectionDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // DataStreamSource<Integer> source = env.fromCollection(Arrays.asList(1, 3, 5, 7, 9));
        DataStreamSource<Integer> source = env.fromElements(1, 3, 5, 7, 9);
        source.print();
        env.execute();
    }
}
