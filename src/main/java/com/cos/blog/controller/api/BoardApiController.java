package com.cos.blog.controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplyDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) {
        boardService.boardSave(board, principalDetail.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> boardDelete(@PathVariable int id) {
        boardService.boardDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board) {
        boardService.boardUpdate(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @PostMapping("/api/board/{boardId}/reply")
    // 데이터 받을 때 컨트롤러에서 dto를 만들어서 받는게 좋다
    // 여기선 dto를 사용하지 않는 이유는
    public ResponseDto<Integer> replySave(@RequestBody ReplyDto reply) {

        boardService.replySave(reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
