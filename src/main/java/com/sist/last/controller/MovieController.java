package com.sist.last.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import com.sist.last.dao.*;
import com.sist.last.entity.*;

@Controller
public class MovieController {
	@Autowired
	private MovieDAO dao;
	
	@GetMapping("/movie/list")
	public String movie_list(String page,String cno,Model model) {
		if(page==null)
			page="1";
		if(cno==null)
			cno="1";
		
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-rowSize;//0 (rownum=1,limit=0)
		List<MovieEntity> list=dao.movieListData(Integer.parseInt(cno),start);
		int totalpage=dao.movieTotalPage(Integer.parseInt(cno));
		final int BLOCK=5;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		//데이터 전송
		model.addAttribute("curpage",curpage);
		model.addAttribute("totalpage",totalpage);
		model.addAttribute("startPage",startPage);
		model.addAttribute("endPage",endPage);
		model.addAttribute("title",Integer.parseInt(cno)==1?"현재상영작":"개봉예정작");
		model.addAttribute("list",list);
		model.addAttribute("cno",cno);
		model.addAttribute("main_content","movie/list");
		return "main";
	}
	
//	@PostMapping("/movie_find")
//	public String movie_find(String page,String title, Model model) {
//		if(page==null)
//			page="1";
//		if(title==null)
//			title="여름";
//		
//		int curpage=Integer.parseInt(page);
//		int rowSize=12;
//		int start=(rowSize*curpage)-rowSize;//0 (rownum=1,limit=0)
//		List<MovieEntity> list=dao.movieFindData(title,start);
//		int totalpage=dao.movieFindTotalPage(title);
//		final int BLOCK=5;
//		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
//		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
//		if(endPage>totalpage)
//			endPage=totalpage;
//		//데이터 전송
//		model.addAttribute("curpage",curpage);
//		model.addAttribute("totalpage",totalpage);
//		model.addAttribute("startPage",startPage);
//		model.addAttribute("endPage",endPage);
//		model.addAttribute("list",list);
//		model.addAttribute("title",title);
//		model.addAttribute("main_content","movie/find");
//		return "movie/find";
//	}
}
