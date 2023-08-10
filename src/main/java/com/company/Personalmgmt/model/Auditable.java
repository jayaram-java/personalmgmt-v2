package com.company.Personalmgmt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

	@Column(name = "created_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	protected Date createdDate;

	@Column(name = "modified_date", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date modifiedDate;

	@CreatedBy
	@Column(name = "created_by")
	protected T createdBy;

	@LastModifiedDate
	@Column(name = "modified_by")
	protected T modifiedBy;

}
