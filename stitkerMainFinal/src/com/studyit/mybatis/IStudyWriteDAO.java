/*===============
 IInformDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;


public interface IStudyWriteDAO
{
	public int studyOpenAdd(StudyWriteDTO studywrite);
	public int meetDayAdd(StudyWriteDTO studywrite);
	public int modify(StudyWriteDTO studywrite);
	public int deleteMeetday(String study_code);
	
	public StudyWriteDTO searchStudy(String study_code);
	public ArrayList<MeetdayDTO> searchMeetday(String study_code);

}
