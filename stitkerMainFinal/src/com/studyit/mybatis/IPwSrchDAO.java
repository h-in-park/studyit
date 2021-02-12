/*===============
 IPwSrchDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public interface IPwSrchDAO
{
	public ArrayList<PwSrchDTO> pqList();
}
