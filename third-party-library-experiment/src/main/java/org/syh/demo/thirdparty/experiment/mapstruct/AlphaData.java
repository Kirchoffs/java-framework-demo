package org.syh.demo.thirdparty.experiment.mapstruct;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlphaData {
    public Item mainItem;
    public List<Item> items;
    public String alphaId;
    public String alphaHash;

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
        public EnumValue enumValue;
    }

    public enum EnumValue {
        VALUE_ONE,
        VALUE_TWO,
        VALUE_THREE
    }
}
