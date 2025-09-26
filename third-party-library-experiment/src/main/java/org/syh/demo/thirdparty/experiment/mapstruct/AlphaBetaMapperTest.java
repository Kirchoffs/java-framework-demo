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
            .alphaEnum(AlphaData.AlphaEnum.X)
            .optionalValue("valid-optional-value")
            .build();

        // Alpha -> Beta
        BetaData beta = AlphaBetaMapper.INSTANCE.toBeta(alpha);

        System.out.println("betaId = " + beta.getBetaId()); // 42
        System.out.println("betaHash = " + beta.getBetaHash()); // null
        System.out.println("mainItem.value.enumValue = " + beta.getMainItem().getValue().getEnumValue()); // VALUE_ONE
        System.out.println("items[0].value.enumValue = " + beta.getItems().get(0).getValue().getEnumValue()); // VALUE_TWO
        System.out.println("betaEnum = " + beta.getBetaEnum()); // X
        System.out.println("optionalValue = " + beta.getOptionalValue()); // valid-optional-value
        System.out.println();

        // Beta -> Alpha
        AlphaData alphaConversed = AlphaBetaMapper.INSTANCE.toAlpha(beta);

        System.out.println("alphaId = " + alphaConversed.getAlphaId()); // 42
        System.out.println("alphaHash = " + alphaConversed.getAlphaHash()); // null
        System.out.println("mainItem.value.enumValue = " + alphaConversed.getMainItem().getValue().getEnumValue()); // VALUE_ONE
        System.out.println("items[0].value.enumValue = " + alphaConversed.getItems().get(0).getValue().getEnumValue()); // VALUE_TWO
        System.out.println("alphaEnum = " + alphaConversed.getAlphaEnum()); // X
        System.out.println("optionalValue = " + alphaConversed.getOptionalValue()); // valid-optional-value
    }
}
