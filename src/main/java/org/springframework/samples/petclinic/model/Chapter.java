package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "chapters",
/* Chapter indexes are unique within each story */
	uniqueConstraints={
		    @UniqueConstraint(columnNames = {"story_id", "index"})
	})
public @Data class Chapter extends BaseEntity {

	@NotNull
	@Positive
	private Integer index;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	@Column(columnDefinition = "TEXT")
	private String body;
	
	@NotNull
	@Column(name = "is_published")
	private Boolean isPublished;
	
	@ManyToOne(optional=false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Story story;
	
}
