package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
                                Pageable pageable) { // 컨트롤러에서 세션을 어떻게 찾을까?

        model.addAttribute("boards", boardService.boardList(pageable));

        return "index"; //viewResolver 작동
    }

    @GetMapping("/board/search")
    public String boardSearch(@RequestParam(value = "word") String word, @RequestParam(required = false, defaultValue = "") String field, Model model, Pageable pageable) {

        model.addAttribute("boardList", boardService.searchContent(word));

        return "index";
    }

    @GetMapping("/board/{id}")
    public String boardRead(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("board", boardService.boardRead(id));
        boardService.updateView(id);
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.boardRead(id));
        return "board/updateForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {

        return "board/saveForm";
    }
}
