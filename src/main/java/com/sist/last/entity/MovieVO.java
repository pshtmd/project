package com.sist.last.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieVO {
	//출력용
	private int totalPage,startPage,endPage,curpage;
	private int mno;
	private int cno;
	private String title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey;
}
