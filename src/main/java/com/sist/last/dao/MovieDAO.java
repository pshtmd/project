package com.sist.last.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.last.entity.MovieEntity;

import java.util.*;
@Repository
public interface MovieDAO extends JpaRepository<MovieEntity, Integer> {
	//목록
	@Query(value="SELECT mno,cno,title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey "
			+"FROM movie "
			+"WHERE cno=:cno ORDER BY mno ASC "
			+"limit :start,12"
			,nativeQuery = true)
	public List<MovieEntity> movieListData(@Param("cno") Integer cno,@Param("start") Integer start);
	//페이징
	@Query(value="SELECT CEIL((SELECT COUNT(*) FROM movie WHERE cno=:cno)/12.0)"
			,nativeQuery = true)
	public int movieTotalPage(@Param("cno") Integer cno);
	
	//상세보기 => Cookie
	
	//메서드 Query
	public MovieEntity findByMno(int mno);//WHERE mno=:mno
	/* <검색 findByXxxContaining>
	 * Like findBytitleStarting() => A%
	 * Like findBytitleEnding() => %A
	 */
	public List<MovieEntity> findByTitleContaining(String title);
	//save, delete => findAll 
	
	//찾기
	@Query(value="SELECT mno,cno,title,time,grade,reserve,genre,regdate,director,actor,showuser,poster,story,mkey "
			+"FROM movie "
			+"WHERE title LIKE CONCAT('%',:title,'%') "
			+"limit :start,12"
			,nativeQuery = true)
	public List<MovieEntity> movieFindData(@Param("title") String title,@Param("start") Integer start);
	@Query(value="SELECT CEIL((SELECT COUNT(*) FROM movie WHERE title LIKE('%',:title,'%')/12.0)"
			,nativeQuery = true)
	public int movieFindTotalPage(@Param("title") String title);
}
