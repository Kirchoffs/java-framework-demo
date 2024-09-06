package org.syh.demo.flink.hello;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;

public class WordCountStreamUnboundedLocalDemo {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(new Configuration());
        // env.setParallelism(2);

        DataStreamSource<String> socketDS = env.socketTextStream("localhost", 6174);
        
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = socketDS
            .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {
                String[] words = value.split("\\s+");
                for (String word : words) {
                    out.collect(Tuple2.of(word, 1));
                }
            })
            .setParallelism(2)
            .returns(Types.TUPLE(Types.STRING, Types.INT))
            .keyBy(value -> value.f0)
            .sum(1);
        
        sum.print();

        try {
            env.execute("WordCountStreamUnboundedLocalDemo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
