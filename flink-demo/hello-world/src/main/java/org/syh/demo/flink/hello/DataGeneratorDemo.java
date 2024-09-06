package org.syh.demo.flink.hello;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.connector.source.util.ratelimit.RateLimiterStrategy;
import org.apache.flink.connector.datagen.source.DataGeneratorSource;
import org.apache.flink.connector.datagen.source.GeneratorFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataGeneratorDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(3);

        DataGeneratorSource<String> dataGeneratorSource = new DataGeneratorSource<>(
            new GeneratorFunction<Long, String>() {
                @Override
                public String map(Long value) throws Exception {
                    return "Number: " + value;
                }
            },
            42,
            RateLimiterStrategy.perSecond(1),
            Types.STRING
        );

        env.fromSource(dataGeneratorSource, WatermarkStrategy.noWatermarks(), "Data Generator Source").print();

        env.execute();
    }
}
