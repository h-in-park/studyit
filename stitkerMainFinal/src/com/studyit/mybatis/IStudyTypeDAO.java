/*===================
  IStudyTypeDAO.java
 - 인터페이스
====================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public interface IStudyTypeDAO
{
	public ArrayList<StudyTypeDTO> stList();

}
