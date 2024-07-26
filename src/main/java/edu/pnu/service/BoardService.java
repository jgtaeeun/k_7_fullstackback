package edu.pnu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepo;
	
	public List<Board> getBoards(){
		return boardRepo.findAll();
	}
	
	public Board getBoard(Long id) {
		return boardRepo.findById(id).get();
	}

	public Board saveBoard(Board board) {
		// TODO Auto-generated method stub
		boardRepo.save(board);
		return board;
	}
	
	public Board updateBoard(Board board) {
		 Board existingBoard = boardRepo.findById(board.getId())
                 .orElseThrow(() -> new IllegalArgumentException("Board not found"));

		 existingBoard.setContent(board.getContent());
		 existingBoard.setTitle(board.getTitle());
		 existingBoard.setWriter(board.getWriter());

		 return boardRepo.save(existingBoard);
	}

    public void deleteBoard(Long id) {
        Board existingBoard = boardRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        boardRepo.delete(existingBoard);
    }
}
