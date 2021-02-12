package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IJoinAjaxDAOImpl implements IJoinAjaxDAO
{
	@Autowired
	JoinAjaxDAO dao;

	@Override
	public int idCheck(String id) throws Exception
	{
		int result = dao.idCheck(id);
		return result;
	}



	

	
}
