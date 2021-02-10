package kr.or.connect.diseasepredict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class htmlController {
	@GetMapping("/index")
	public String index() {
		return "index.html";
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
	@GetMapping("/login")
	public String login() {
		return "login.html";
	}
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
		return "covid19.html";
	}
}
