package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IStudySearchDAO
{
	// 스터디 리스트 조회
	public ArrayList<StudySearchDTO> list(StudySearchDTO dto);
	
	// 게시물 수 조회
	public int count(@Param("searchKey") String searchKey,@Param("searchValue") String searchValue,@Param("loc_lc") String loc_lc, @Param("loc_mc")String loc_mc, @Param("study_type") String study_type);
//	public int count();
	
	// 스터디 키워드 검색 - 스터디명 
	public ArrayList<StudySearchDTO> studyTitleSearch(@Param("searchValue") String searchValue,@Param("start") int start, @Param("end") int end);
	
	// 스터디 키워드 검색 - 리더 
	public ArrayList<StudySearchDTO> studyLeaderSearch(@Param("searchValue") String searchValue,@Param("start") int start, @Param("end") int end);
	
	// 스터디 키워드 검색 - 스터디번호 
	public ArrayList<StudySearchDTO> studyNumSearch(@Param("searchValue") String searchValue,@Param("start") int start, @Param("end") int end);
	
	// 지역 대분류 
	public ArrayList<StudySearchDTO> loc_lc();

	// 지역 중분류 
	public ArrayList<StudySearchDTO> loc_mc();
	
	// 스터디 타입 
	public ArrayList<StudySearchDTO> study_type();

	// 사용자 등급
	public ArrayList<StudySearchDTO> rank();
	
	// 카테고리- 지역 대분류 + 중분류 + 스터디 유형 검색
	public ArrayList<StudySearchDTO> categorySearch(@Param("loc_lc") String loc_lc,@Param("loc_mc") String loc_mc,@Param("studyType") String studyType,@Param("start") int start, @Param("end") int end);
	
	
}
