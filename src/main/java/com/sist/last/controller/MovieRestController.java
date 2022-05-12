package com.sist.last.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.last.dao.MovieDAO;
import com.sist.last.entity.MovieEntity;
import com.sist.last.entity.MovieVO;

import groovyjarjarantlr4.v4.parse.ANTLRParser.exceptionGroup_return;

import java.util.*;
@RestController
public class MovieRestController {
	@Autowired
	private MovieDAO dao;
	
	@PostMapping("movie/detail")
	public MovieEntity movie_detail(int mno) {
		MovieEntity vo=dao.findByMno(mno);
		return vo;
	}
	
	@PostMapping("movie/movie_find")
	public JSONArray movie_find(String page,String sd){
	System.out.println("movie_find 시작!!");
	String result="";
	JSONArray arr=new JSONArray();
	try {
		
	if(page==null)
		page="1";
	if(sd==null)
		sd="가";
	int curpage=Integer.parseInt(page);
	int rowSize=12;
	int start=((curpage*rowSize)-rowSize);
	int totalpage=dao.movieFindTotalPage(sd);
	final int BLOCK=5;
	int startPage=((curpage-1)/BLOCK*BLOCK)+1;
	int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	List<MovieEntity> list=dao.movieFindData(sd, start);
	
	int i=0;
	for(MovieEntity ent:list) {
		JSONObject obj=new JSONObject();
		obj.put("title",ent.getTitle());
		obj.put("poster",ent.getPoster());
		if(i==0) {
			obj.put("curpage",curpage);
			obj.put("totalpage",totalpage);
			obj.put("startPage",startPage);
			obj.put("endPage",endPage);
			obj.put("header","검색결과");
		}
		arr.add(obj);
		i++;
	}
//	result=arr.toJSONString();
	}catch(Exception ex) {}
	return arr;
	}
}
