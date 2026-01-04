package org.syh.demo.thirdparty.experiment.mapstruct;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BetaData {
    public Item mainItem;
    public List<Item> items;
    public long betaId;
    public String betaHash;
    public BetaEnum betaEnum;
    public Optional<String> optionalValue;

    public void setOptionalValue(String optionalValue) {
        this.optionalValue = Optional.ofNullable(optionalValue);
    }

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
        public String enumValue;
    }

    public enum BetaEnum {
        X,
        Y,
        Z
    }
}
