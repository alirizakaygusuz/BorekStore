package com.alirizakaygusuz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.entity.Borek;


@Mapper(componentModel = "spring")
public interface BorekMapper {
	

    DtoBorek borekToDtoBorek(Borek borek);

    
    @BeanMapping(ignoreByDefault = false)
    Borek dtoBorekIUToBorek(DtoBorekIU dtoBorekIU);

    DtoBorekIU dtoBorekToDtoBorekIU(DtoBorek dtoBorek);
    
    @BeanMapping(ignoreByDefault = false)
    void updateBorekFromDtoBorekIU(DtoBorekIU dtoBorekIU, @MappingTarget Borek borek);

	

}
