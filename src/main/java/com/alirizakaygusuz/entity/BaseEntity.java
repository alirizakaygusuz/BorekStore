package com.alirizakaygusuz.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	@Column(updatable = false, nullable = false)
	@NotNull(message = "Creation timestamp cannot be null")
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	@NotNull(message = "Update timestamp cannot be null")
	private LocalDateTime updatedAt;

	@CreatedBy
	@Column(updatable = false, nullable = false)
	@NotBlank(message = "CreatedBy cannot be blank")
	private String createdBy;

	@LastModifiedBy
	@Column(nullable = false)
	@NotBlank(message = "UpdatedBy cannot be blank")
	private String updatedBy;

}
