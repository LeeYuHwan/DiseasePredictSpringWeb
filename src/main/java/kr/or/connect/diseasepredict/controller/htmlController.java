package kr.or.connect.diseasepredict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class htmlController {
	@GetMapping("/index")
	public String index() {
		return "index.jsp";
	}
	@GetMapping("/blank")
	public String blank() {
		return "blank.html";
	}
	@GetMapping("/buttons")
	public String buttons() {
		return "buttons.html";
	}
	@GetMapping("/cards")
	public String cards() {
		return "cards.html";
	}
	@GetMapping("/charts")
	public String charts() {
		return "charts.html";
	}
	@GetMapping("/forgot_password")
	public String forgot_password() {
		return "forgot_password.html";
	}
	/*@GetMapping("/login")
	public String login() {
		return "/login_test.jsp";
	}
	@GetMapping("/login_success")
	public String login_success() {
		return "/login_test_success.jsp";
	}*/
	@GetMapping("/register")
	public String register() {
		return "register.html";
	}
	@GetMapping("/tables")
	public String tables() {
		return "tables.html";
	}
	@GetMapping("/utilities_animation")
	public String utilities_animation() {
		return "utilities_animation.html";
	}
	@GetMapping("/utilities_border")
	public String utilities_border() {
		return "utilities_border.html";
	}
	@GetMapping("/utilities_color")
	public String utilities_color() {
		return "utilities_color.html";
	}
	@GetMapping("/utilities_other")
	public String utilities_other() {
		return "utilities_other.html";
	}
	@GetMapping("/map")
	public String map() {
		return "map.html";
	}
	@GetMapping("/covid19")
	public String covid() {
		return "covid19.jsp";
	}
	
	//게시판 컨트롤러
	/*@GetMapping("/board_list")
	public String BoardList() {
		return "/board/board_list.jsp";
	}*/
	@GetMapping("/board_modify")
	public String BoardModify() {
		return "/board/board_modify.jsp";
	}
	/*@GetMapping("/board_get")
	public String BoardGet() {
		return "/board/board_get.jsp";
	}*/
	@GetMapping("/board_get_tmp")
	public String BoardGet_tmp() {
		return "/board/board_get_tmp.jsp";
	}
	@GetMapping("/board_register")
	public String BoardRegister() {
		return "/board/board_register.jsp";
	}
}
