/*========================
 StudentController.java
=======================*/
package com.studyit.mvc;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InformListController
{
   // SqlSession 을 활용하여 마이바티스 객체 의존성(자동) 주입 
   @Autowired
   private SqlSession sqlSession;
   
   // 매개변수를 등록하는 과정에서 매개변수 목록에 적혀있는
   // 클래스의 객체 정보는 스프링이 제공한다. 
   
   // 사용자의 요청 주소와 메소드를 매핑하는 과정 필요.
   // RequestMapping(value = "/요청주소", method = 전송방식)
   // 이 때, 전송 방식은 submit 액션인 경우만 POST
   // 나머지 모든 전송 방식은 GET 으로 처리한다. 
   
   @RequestMapping(value = "/informlist.action", method = RequestMethod.GET)
   public String informList(Model model)
   {
      String result = null;
      
      IInformDAO inform = sqlSession.getMapper(IInformDAO.class);
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      
      model.addAttribute("count", inform.count());
      model.addAttribute("list" , inform.list());
      model.addAttribute("imList", interest.imList());
      
      result = "/WEB-INF/views/Inform_list.jsp";
      
      return result;
   }
   
   @RequestMapping(value = "/informinsertform.action", method = RequestMethod.GET)
   public String informInsertForm(Model model)
   {
      String result = null;
      
      IInterestDAO interest = sqlSession.getMapper(IInterestDAO.class);
      
      model.addAttribute("imList", interest.imList());
      
      result = "/WEB-INF/views/Inform_write.jsp";
      return result;
   }
   
   @RequestMapping(value ="/informinsert.action", method = RequestMethod.POST)
   public String studentInsert(InformDTO inform)
   {
      IInformDAO dao = sqlSession.getMapper(IInformDAO.class);
      
      dao.add(inform);
      
      return "redirect:informlist.action";
   }
   
   
}