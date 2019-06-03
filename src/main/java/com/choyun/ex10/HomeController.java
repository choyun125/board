package com.choyun.ex10;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.choyun.domain.LoginDTO;
import com.choyun.domain.MemberVO;
import com.choyun.service.MemberService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private MemberService mService;
	
	@Inject
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		
		return "home";
	}
	
	@RequestMapping(value = { "/login" }, method = { RequestMethod.GET })
	public String login() {
		
		return "/member/MemberLogin";
		
	}
	
	@RequestMapping(value = { "/logout" }, method = { RequestMethod.GET })
	public String logout(HttpServletRequest request) {
		
		request.getSession().setAttribute("loginSession", null);
		return "redirect:/";
		
	}
	
	@RequestMapping(value = { "/login/post" }, method = { RequestMethod.POST })
	public String checkLogin(LoginDTO dto, HttpSession session, Model model, RedirectAttributes redirectAttribute) {
		
		MemberVO vo = null;
		String returnValue = "";
		
		try {
			
			vo = mService.readMemberData(dto.getUserid());
			
			if (vo == null) {
				redirectAttribute.addFlashAttribute("result", "fail");
				redirectAttribute.addFlashAttribute("cause", "id");
				returnValue = "redirect:/login";
			}
			
			else {
				if (passwordEncoder.matches(dto.getUserpw(), vo.getUserpw())) {
					model.addAttribute("loginSession", vo);
					
					returnValue = "redirect:/board/list";
				}
				else {
					redirectAttribute.addFlashAttribute("result", "fail");
					redirectAttribute.addFlashAttribute("cause", "password");
					returnValue = "redirect:/login";
				}
				
			}
			
		}
		
		catch (Exception e) {

		}
		
		return returnValue;
	}
	
	@RequestMapping(value = "/doA", method = RequestMethod.GET)
	public String doA(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/doB", method = RequestMethod.GET)
	public String doB(Locale locale, Model model) {
		
		return "home";
	}
	
}
