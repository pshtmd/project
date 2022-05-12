package com.sist.last.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/*
 no int AI PK 
cno int 
title varchar(200) 
singer varchar(300) 
album varchar(300) 
state varchar(20) 
idcrement int 
poster varchar(500) 
mkey varchar(100)
 */
@Entity(name="music")
@Setter
@Getter
//table 컬럼명과 일치시키면  함수 자동 생성됨 => save(insert/update일괄처리 ),delete,findAll,findOne)
public class MusicEntity {
	//save함수 생성됨
	@Id // PK
	private int no;
	private int cno,idcrement;
	private String title,singer,album,state,poster,mkey;
	
}
