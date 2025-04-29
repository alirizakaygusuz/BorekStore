package com.alirizakaygusuz.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.alirizakaygusuz.dto.DtoStore;
import com.alirizakaygusuz.dto.DtoStoreIU;
import com.alirizakaygusuz.entity.Store;

@Mapper(componentModel = "spring")
public interface StoreMapper {

	DtoStore storeToDtoStore(Store store);

	
	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "name", source = "name")
	Store dtoStoreIUToStore(DtoStoreIU dtoStoreIU);

	@BeanMapping(ignoreByDefault = true)
	@Mapping(target = "name", source = "name")
	void update(DtoStoreIU dtoStoreIU, @MappingTarget Store store);

}
