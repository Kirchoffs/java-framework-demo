package org.syh.demo.flink.demo.water.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WaterLevelSensor {
    private String id;
    private Long timestamp;
    private Integer waterLevel;
}
