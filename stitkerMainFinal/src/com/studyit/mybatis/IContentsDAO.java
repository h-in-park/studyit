package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IContentsDAO
{
	// 스터디 컨텐츠 조회
	public ArrayList<ContentsDTO> contentslist(@Param("studycode") String studycode, @Param("start") int start, @Param("end") int end);
	
	// 스터디 상세 조회
	public ArrayList<ContentsDTO> detail(@Param("studycode") String studycode, @Param("contentcode")String contentcode);
		
	// 스터디 컨텐츠 등록
	public int add(ContentsDTO contents);
	
	// 스터디 컨텐츠 수정
	public int modify(ContentsDTO contents);
	
	// 스터디 컨텐츠 삭제
	public int remove(ContentsDTO contents);
	
	// 조회수 증가
	public int hitcount(@Param("contentcode")String contentcode);
	
	// PARTI_CODE 조회
	public String particode(@Param("studycode")String studycode, @Param("id") String id);

	// 스터디 수정 조회
	public ContentsDTO update(@Param("contentcode")String contentcode);

	public int count(@Param("studycode")String studycode);
}
