/*==========================================
  JoinAjaxDAO.java		
===========================================*/

package com.studyit.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class JoinAjaxDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	//아이디 체크
	public int idCheck(String id) 
	{
		System.out.println("===> Mybatis로 idCheck");
		int result = sqlsession.selectOne("com.studyit.mybatis.IJoinAjaxDAO.idCheck", id);
		return result;
	}
	
	/*
	 * Sample2VO vo = new Sample2VO(); vo.setVid(12);
	 * sqlSession.selectOne("sample.spSelect2",vo);
	 * System.out.println(vo.getRetval());
	 */
	
}





























