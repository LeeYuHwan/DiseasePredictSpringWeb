package kr.or.connect.diseasepredict.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.connect.diseasepredict.board.dto.BoardVO;
import kr.or.connect.diseasepredict.service.BoardService;
import kr.or.connect.diseasepredict.service.EncryptHelper;

@Controller
public class BoardController 
{
	@Autowired
	private BoardService service;
	@Autowired
	private EncryptHelper encryptHelper;
	
	@GetMapping("/board_list")
	public String list(Model model)
	{
		List<BoardVO> boardList = service.getList();
		model.addAttribute("list", boardList);
		long total = service.getTotalCount();
		System.out.println("total: " + total);
		//model.addAttribute("pageMaker", new PageDTO(cri, total));
		
		return "/board/board_list.jsp";
	}
	
	@GetMapping("/board_search")
	public String search(@RequestParam("keyword") String keyword,
            			@RequestParam("select") String select,
            			Model model)
	{
		//T C W TC TW TWC
		List<BoardVO> boardList;
		if(select.equals("T")) {
			boardList = service.getTitle(keyword);
			System.out.println("제목 검색");
		}
		else if(select.equals("C")) {
			boardList = service.getContent(keyword);
			System.out.println("내용 검색");
		}
		else if(select.equals("W")) {
			boardList = service.getWriter(keyword);	
			System.out.println("작성자 검색");
		}
		else if(select.equals("TC")) {
			boardList = service.getContentTitle(keyword);
			System.out.println("제목이나 내용 검색");
		}
		else if(select.equals("TW")) {
			boardList = service.getTitleWriter(keyword);
			System.out.println("제목이나 작성자 검색");
		}
		else if(select.equals("TWC")) {
			boardList = service.getTitleContentWriter(keyword);
			System.out.println("제목이나 작성자나 내용 검색");
		}
		else {
			boardList = service.getList();
			System.out.println("그외 검색");
		}		
		System.out.println(keyword);
		System.out.println(select);
		model.addAttribute("list", boardList);
		long total = service.getTotalCount();
		System.out.println("total: " + total);
		//model.addAttribute("pageMaker", new PageDTO(cri, total));
		
		return "/board/board_list.jsp";
	}
	
	/*@GetMapping("/register")
	//@PreAuthorize("isAuthenticated()")
	public void register() {
		
	}*/	
	private String getIp(HttpServletRequest request) {
		 
        String ip = request.getHeader("X-Forwarded-For");
 
 
        if (ip == null) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null) {
            ip = request.getHeader("WL-Proxy-Client-IP"); // 웹로직
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null) {
            ip = request.getRemoteAddr();
        }
         
        return ip;
    }
	
	@PostMapping("/board_register_add")
	public String register(@ModelAttribute BoardVO boardVO, Model model, HttpServletRequest req)
	{
		String passwd = encryptHelper.encrypt(boardVO.getPasswd());
		boardVO.setPasswd(passwd);	
		String ip = getIp(req);
		boardVO.setIp(ip);
		
		//System.out.println(ip);
		//System.out.println(passwd);
		//System.out.println(boardVO.getIp());
		//System.out.println(boardVO.getPasswd());
		
		service.insert(boardVO);	
		return "redirect:board_list";
	}
	
	@GetMapping("/board_get")
	public String get(@RequestParam("bno") Long bno, Model model)
	{	
		System.out.println(bno);
		BoardVO readBoard = service.read(bno);
		System.out.println("/read");
		model.addAttribute("board", readBoard);
		
		return "/board/board_get.jsp";
	}
	/*
	//@PreAuthorize("principal.username == #board.writer")
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr)
	{
		System.out.println("modify:" + board);
		
		if (service.modify(board)) 
		{
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}
	
	//@PreAuthorize("principal.username == #writer")
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr, String writer)
	{
		System.out.println("remove..." + bno);
		
		if (service.remove(bno))
		{
			rttr.addFlashAttribute("result", "success");
		}
		
		return "redirect:/board/list" + cri.getListLink();
	}*/
	
}