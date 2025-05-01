package com.alirizakaygusuz.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoBase {
	
	private Long id;
	
    private LocalDateTime createdAt;

   
    private LocalDateTime updatedAt;

    
    private String createdBy;

   
    private String updatedBy;


	


}
