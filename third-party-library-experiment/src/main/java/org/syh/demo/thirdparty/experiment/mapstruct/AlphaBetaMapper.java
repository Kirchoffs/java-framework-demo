package org.syh.demo.thirdparty.experiment.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.syh.demo.thirdparty.experiment.mapstruct.AlphaData.AlphaEnum;
import org.syh.demo.thirdparty.experiment.mapstruct.BetaData.BetaEnum;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AlphaBetaMapper {
    AlphaBetaMapper INSTANCE = Mappers.getMapper(AlphaBetaMapper.class);

    // Alpha -> Beta
    @Mappings({
        @Mapping(source = "alphaId", target = "betaId"),
        @Mapping(target = "betaHash", ignore = true), // We can actually omit this line.
        @Mapping(source = "alphaEnum", target = "betaEnum")
    })
    BetaData toBeta(AlphaData alpha);

    BetaData.Value toBeta(AlphaData.Value value);

    default BetaEnum map(AlphaEnum alphaEnum) {
        return alphaEnum == null ? null : BetaEnum.valueOf(alphaEnum.name());
    }

    default String map(AlphaData.EnumValue value) {
        return value == null ? null : value.name();
    }

    // Beta -> Alpha
    @Mappings({
        @Mapping(source = "betaId", target = "alphaId"),
        @Mapping(target = "alphaHash", ignore = true),
        @Mapping(source = "betaEnum", target = "alphaEnum")
    })
    AlphaData toAlpha(BetaData beta);

    AlphaData.Value toAlpha(BetaData.Value value);

    default AlphaEnum map(BetaEnum betaEnum) {
        return betaEnum == null ? null : AlphaEnum.valueOf(betaEnum.name());
    }

    default AlphaData.EnumValue map(String value) {
        return value == null ? null : AlphaData.EnumValue.valueOf(value);
    }

    default String map(Optional<String> optionalValue) {
        return optionalValue.orElse(null);
    }
}
