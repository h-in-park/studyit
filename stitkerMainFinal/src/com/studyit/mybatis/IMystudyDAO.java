package com.studyit.mybatis;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

public interface IMystudyDAO
{
	public ArrayList<MystudyDTO> endlist( @Param("id") String id);
	public ArrayList<MystudyDTO> nowlist( @Param("id") String id);
	public ArrayList<MystudyDTO> cancellist( @Param("id") String id);
	public ArrayList<MystudyDTO> upcominglist( @Param("id") String id);
	public int endcount( @Param("id") String id);
	public int nowcount( @Param("id") String id);
	public int upcomingcount( @Param("id") String id);
	public int cancelcount( @Param("id") String id);
}
 