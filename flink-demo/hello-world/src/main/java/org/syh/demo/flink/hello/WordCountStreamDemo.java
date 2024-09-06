package org.syh.demo.flink.hello;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.connector.file.src.reader.TextLineInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.KeyedStream;

public class WordCountStreamDemo {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        
        FileSource<String> source = FileSource.forRecordStreamFormat(new TextLineInputFormat(), new Path("input/words.txt")).build();
        DataStreamSource<String> dataStream = env.fromSource(source, WatermarkStrategy.noWatermarks(), "File Source");

        SingleOutputStreamOperator<Tuple2<String, Integer>> wordAndOneStreamOperator = 
            dataStream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                @Override
                public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
                    for (String word : value.split("\\s+")) {
                        out.collect(Tuple2.of(word, 1));
                    }
                }
            });

        KeyedStream<Tuple2<String, Integer>, String> wordAndOneKeyedStream = 
            wordAndOneStreamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
                @Override
                public String getKey(Tuple2<String, Integer> value) {
                    return value.f0;
                }
            });

        SingleOutputStreamOperator<Tuple2<String, Integer>> sumOperator = wordAndOneKeyedStream.sum(1);
        sumOperator.print();

        try {
            env.execute("WordCountStreamDemo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
