package org.syh.demo.thirdparty.experiment.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.mapstruct.ReportingPolicy;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AlphaBetaMapper {
    AlphaBetaMapper INSTANCE = Mappers.getMapper(AlphaBetaMapper.class);

    // Alpha -> Beta
    @Mappings({
        @Mapping(source = "alphaId", target = "betaId"),
        @Mapping(target = "betaHash", ignore = true)
    })
    BetaData toBeta(AlphaData alpha);

    @Mapping(
        source = "enumValue",
        target = "enumValue"
    )
    BetaData.Value toBeta(AlphaData.Value value);

    // Beta -> Alpha
    @Mappings({
        @Mapping(source = "betaId", target = "alphaId"),
        @Mapping(target = "alphaHash", ignore = true)
    })
    AlphaData toAlpha(BetaData beta);

    @Mapping(
        source = "enumValue",
        target = "enumValue"
    )
    AlphaData.Value toAlpha(BetaData.Value value);

    default String map(AlphaData.EnumValue value) {
        return value == null ? null : value.name();
    }

    default AlphaData.EnumValue map(String value) {
        return value == null ? null : AlphaData.EnumValue.valueOf(value);
    }
}
