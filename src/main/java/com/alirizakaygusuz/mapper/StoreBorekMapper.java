package com.alirizakaygusuz.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.entity.StoreBorek;

@Mapper(componentModel = "spring", uses = { StoreMapper.class, BorekMapper.class })
public interface StoreBorekMapper {

	@Mapping(source = "store", target = "store")
	@Mapping(source = "borek", target = "borek")
	DtoStoreBorek storeBorekToDtoStoreBorek(StoreBorek storeBorek);

}
