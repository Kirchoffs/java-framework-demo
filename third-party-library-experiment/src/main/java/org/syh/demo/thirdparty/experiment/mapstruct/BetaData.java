package org.syh.demo.thirdparty.experiment.mapstruct;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BetaData {
    public Item mainItem;
    public List<Item> items;
    public long betaId;
    public String betaHash;

    @Data
    @Builder
    public static class Item {
        public String key;
        public Value value;
    }

    @Data
    @Builder
    public static class Value {
        public int numValue;
        public String strValue;
        public String enumValue;
    }
}
