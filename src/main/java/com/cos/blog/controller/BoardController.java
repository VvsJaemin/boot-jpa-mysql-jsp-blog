package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model) { // 컨트롤러에서 세션을 어떻게 찾을까?

        model.addAttribute("boards", boardService.boardList());

        return "index"; //viewResolver 작동
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {

        return "board/saveForm";
    }
}
