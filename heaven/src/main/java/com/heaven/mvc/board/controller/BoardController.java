package com.heaven.mvc.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.heaven.mvc.board.domain.BoardVO;
import com.heaven.mvc.board.service.BoardService;

@Controller
@SessionAttributes("boardVO")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/board/list")
	public String list(Model model) {
		model.addAttribute("boardList", boardService.list());
		/* src/main/webapp/WEB-INF/views/board/list.jsp */
		return "/board/list";
	}

	/* Read */
	@RequestMapping(value = "/board/read/{seq}")
	public String read(Model model, @PathVariable int seq) {
		model.addAttribute("boardVO", boardService.read(seq));

		return "/board/read";
	}

	/* Write */
	@RequestMapping(value = "/board/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("boardVO", new BoardVO());
		return "/board/write";
	}

	/* Write : 글 등록 버튼 클릭 시 */
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(@Valid BoardVO boardVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/board/write";
		} else {
			/* POST요청을 리디렉션(Redirection)해서 GET 요청으로 보내는 것을
			   PRG(POST-Redirect-GET) 패턴이라고 한다. */
			boardService.write(boardVO);
			return "redirect:/board/list";
		}
	}
	
	/* Edit */
	@RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.GET)
	public String edit(@PathVariable int seq, Model model) {
		BoardVO boardVO = boardService.read(seq);
		model.addAttribute("boardVO", boardVO);
		return "/board/edit";
	}

	@RequestMapping(value = "/board/edit/{seq}", method = RequestMethod.POST)
	public String edit(@Valid @ModelAttribute BoardVO boardVO, BindingResult result, int pwd, SessionStatus sessionStatus, Model model) {
		if (result.hasErrors()) {
			return "/board/edit";
		} else {
			if (boardVO.getPassword() == pwd) {
				boardService.edit(boardVO);
				sessionStatus.setComplete();
				/*
				 * 글 수정 jsp는 추가로 구현해야 한다. 현재는 구성되어있지 않아 목록페이지로 이동하게 해놨음.
				 */
				return "redirect:/board/list";
			}

			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "/board/edit";
		}
	}
	
	/* Delete */
	@RequestMapping(value = "/board/delete/{seq}", method = RequestMethod.GET)
	public String delete(@PathVariable int seq, Model model) {
		model.addAttribute("seq", seq);
		return "/board/delete";
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(int seq, int pwd, Model model) {
		int rowCount;
		BoardVO boardVO = new BoardVO();
		boardVO.setSeq(seq);
		boardVO.setPassword(pwd);

		rowCount = boardService.delete(boardVO);

		if (rowCount == 0) {
			model.addAttribute("seq", seq);
			model.addAttribute("msg", "비밀번호가 일치하지 않습니다.");
			return "/board/delete";
		} else {
			return "redirect:/board/list";
		}
	}

}





































