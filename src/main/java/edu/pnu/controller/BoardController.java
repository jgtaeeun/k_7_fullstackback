package edu.pnu.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
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
	
	//CORS 설정이 모든 필요한 엔드포인트에 대해 올바르게 적용되었는지 확인합니다. 예를 들어, /board/**에 대해 GET, POST만 허용하고 PUT, DELETE는 허용하지 않으므로 모든 요청 메서드가 필요한 경우 해당 메서드를 추가해야 합니다.
		//@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/board/{id}")
	public ResponseEntity<Board> updateBoard(@RequestBody Board b) {
	    log.info("updateBoard: " + b.getTitle());

	  

	    // Save the board using BoardService
	    Board updatedBoard = boardService.updateBoard(b);

	    return ResponseEntity.ok(updatedBoard);
	}
	@DeleteMapping("/board/{id}")
	public ResponseEntity<?> deleteBoard(@PathVariable Long id) {
		   try {
		        boardService.deleteBoard(id);
		        return ResponseEntity.ok("Board deleted successfully");
		    } catch (Exception e) {
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found");
		    }
	}

	
}
