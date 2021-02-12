/*===============
 ILocMcDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public interface ILocMcDAO
{
	public ArrayList<LocMcDTO> lmList();
	public ArrayList<LocMcDTO> lmList2(String loc_lc_code);
}
