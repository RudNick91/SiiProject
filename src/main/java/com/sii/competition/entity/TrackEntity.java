package com.sii.competition.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tracks")
public class TrackEntity {
	@Id
	private Long id;
	@Column
	private String title;
	@Column
	private String music;
	@Column
	private Date date;
	
}
