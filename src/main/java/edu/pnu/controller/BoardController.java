package edu.pnu.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	
	
	@GetMapping("/board")
	public ResponseEntity<?> getBoard(){
		log.info("getBoard:All");
		return ResponseEntity.ok(boardService.getBoards());
		
	}
	@GetMapping("/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Long id){
		log.info("getBoard"+id);
		return ResponseEntity.ok(boardService.getBoard(id));
	}
	@PostMapping("/board")
	public ResponseEntity<Board> createBoard(@RequestBody Board b) {
	    log.info("createBoard: " + b.getTitle());

	    // Create a Board entity from the DTO
	    Board board = new Board();
	    board.setTitle(b.getTitle());
	    board.setContent(b.getContent());
	    board.setWriter(b.getWriter());

	    // Save the board using BoardService
	    Board savedBoard = boardService.saveBoard(board);

	    // Return the saved board with a 201 Created status
	    URI location = ServletUriComponentsBuilder
	            .fromCurrentRequest().path("/{id}")
	            .buildAndExpand(savedBoard.getId()).toUri();

	    return ResponseEntity.created(location).body(savedBoard);
	}
	
}
