package org.syh.demo.thirdparty.experiment.mapstruct;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlphaData {
    public Item mainItem;
    public List<Item> items;
    public String alphaId;
    public String alphaHash;
    public AlphaEnum alphaEnum;
    public String optionalValue;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        public String key;
        public Value value;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
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

    public enum AlphaEnum {
        X,
        Y,
        Z
    }
}
