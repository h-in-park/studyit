/*===============
 IJoinDAO.java
 - 인터페이스
================*/

package com.studyit.mybatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


public interface IJoinDAO
{
	
	public int memberJoin(JoinDTO join);
	

	
}
