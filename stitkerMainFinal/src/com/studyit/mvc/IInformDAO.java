/*===============
 IInformDAO.java
 - 인터페이스
================*/

package com.studyit.mvc;

import java.util.ArrayList;


public interface IInformDAO
{
	public int add(InformDTO i);
	public ArrayList<InformDTO> list();
	public int count();
	public int modify(InformDTO i);
	public int remove(InformDTO i);
	
	public ArrayList<InterestDTO> imList();
	
	
	public InformDTO search(String post_code);
	
	//public ArrayList<InformDTO> infoList() throws SQLException;
}
