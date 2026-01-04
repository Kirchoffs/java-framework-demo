package org.syh.demo.thirdparty.experiment.mapstruct;

import java.util.List;

public class AlphaBetaMapperTest {
    public static void main(String[] args) {
        AlphaData alpha = AlphaData.builder()
            .alphaId("42")
            .alphaHash("should-be-ignored")
            .mainItem(
                AlphaData.Item.builder()
                    .key("main-key")
                    .value(
                        AlphaData.Value.builder()
                            .numValue(314)
                            .strValue("hello")
                            .enumValue(AlphaData.EnumValue.VALUE_ONE)
                            .build()
                    )
                    .build()
            )
            .items(List.of(
                AlphaData.Item.builder()
                    .key("simple-key")
                    .value(
                        AlphaData.Value.builder()
                            .numValue(2718)
                            .strValue("world")
                            .enumValue(AlphaData.EnumValue.VALUE_TWO)
                            .build()
                    )
                    .build()
            ))
            .build();

        // Alpha -> Beta
        BetaData beta = AlphaBetaMapper.INSTANCE.toBeta(alpha);

        System.out.println("betaId = " + beta.getBetaId()); // 42
        System.out.println("betaHash = " + beta.getBetaHash()); // null
        System.out.println("mainItem.value.enumValue = " + beta.getMainItem().getValue().getEnumValue()); // VALUE_ONE
        System.out.println("items[0].value.enumValue = " + beta.getItems().get(0).getValue().getEnumValue()); // VALUE_TWO


        // Beta -> Alpha
        AlphaData alpha2 = AlphaBetaMapper.INSTANCE.toAlpha(beta);

        System.out.println("alphaId = " + alpha2.getAlphaId()); // 42
        System.out.println("alphaHash = " + alpha2.getAlphaHash()); // null
        System.out.println("mainItem.value.enumValue = " + alpha2.getMainItem().getValue().getEnumValue()); // VALUE_ONE
        System.out.println("items[0].value.enumValue = " + alpha2.getItems().get(0).getValue().getEnumValue()); // VALUE_TWO
    }
}
