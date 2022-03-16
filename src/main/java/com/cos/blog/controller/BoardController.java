package com.cos.blog.controller;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index(Model model,
                        @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        @RequestParam(required = false, defaultValue = "") String field,  // 컨트롤러에서 세션을 어떻게 찾을까?
                        @RequestParam(required = false, defaultValue = "") String word) { // 컨트롤러에서 세션을 어떻게 찾을까?

//        Page<Board> ulist = boardService.boardList(pageable, word, field);
        Page<Board> list = boardService.searchTitleOrContent(pageable, word, word);;

        model.addAttribute("boards", list);

        return "index"; //viewResolver 작동
    }

//    @GetMapping("/board/search")
//    public String boardSearch(@RequestParam(value = "word") String word, @RequestParam(required = false, defaultValue = "") String field, Model model, Pageable pageable) {
//
//        model.addAttribute("boardList", boardService.searchContent(word));
//
//        return "index";
//    }

    @GetMapping("/board/{id}")
    public String boardRead(@PathVariable int id, Model model, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("board", boardService.boardRead(id));

        viewCountCookie(id, request, response);

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


    private void viewCountCookie(int id, HttpServletRequest request, HttpServletResponse response) {
        Cookie viewCookie = null;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                //만들어진 쿠키들을 확인하며, 만약 들어온 적 있다면 생성되었을 쿠키가 있는지 확인
                if (cookies[i].getName().equals("|" + id + "|")) {
                    //찾은 쿠키를 변수에 저장
                    viewCookie = cookies[i];
                }
            }
        }
        //==========================================
        //만들어진 쿠키가 없음을 확인하고 생성 -> 조회수 증가
        if (viewCookie == null) {
            try {
                //이 페이지에 왔다는 증거용(?) 쿠키 생성
                Cookie newCookie = new Cookie("|" + id + "|", "readCount");
                response.addCookie(newCookie);

                //쿠키가 없으니 증가 로직 진행
                boardService.updateView(id);

            } catch (Exception e) {
                e.getStackTrace();
            }
            //만들어진 쿠키가 있으면 조회수 증가 진행하지 않음
        } else {
            String value = viewCookie.getValue();
        }
    }
}
