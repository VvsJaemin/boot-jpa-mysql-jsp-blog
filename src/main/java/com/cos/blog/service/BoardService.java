package com.cos.blog.service;

import com.cos.blog.dto.ReplyDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final ReplyRepository replyRepository;

    private final UserRepository userRepository;

    @Transactional
    public void boardSave(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<Board> searchContent(String word) {

        List<Board> searchBoardList =null;

            searchBoardList = boardRepository.findByContentContaining(word);

        return searchBoardList;
    }

    @Transactional(readOnly = true)
    public Board boardRead(int id) {

        return boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public int updateView(int id) {

        return boardRepository.updateView(id);
    }

    public Board boardCount(Board board) {
        Board findBoard = boardRepository.findById(board.getCount()).get();
        findBoard.setCount(findBoard.getCount() + 1);
        boardRepository.save(findBoard);

        return findBoard;
    }

    @Transactional
    public void boardDelete(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void boardUpdate(int id, Board requestBoard) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
        }); // 영속화 완료
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 해당 함수 종료시 트랜잭션이 종료 이 때 더티체킹 - 자동 업데이트 -> db flush
    }

    @Transactional
    public void replySave(ReplyDto replyDto) {
        int result = replyRepository.mSave(replyDto.getUserId(), replyDto.getBoardId(), replyDto.getContent());
    }

    @Transactional
    public void replyDelete(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
