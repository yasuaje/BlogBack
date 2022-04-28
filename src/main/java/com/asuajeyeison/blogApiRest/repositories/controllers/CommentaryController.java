package com.asuajeyeison.blogApiRest.repositories.controllers;

import com.asuajeyeison.blogApiRest.dto.CommentaryDTO;
import com.asuajeyeison.blogApiRest.services.CommentaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentaryController {

    @Autowired
    private CommentaryService commentaryService;

    @GetMapping("/publications/{publicationId}/comments")
    public List<CommentaryDTO> listarCommentsByPublicationId(@PathVariable(value = "publicationId") Long publicationId) {
        return commentaryService.obtenerCommentsByPublicationId(publicationId);
    }

    @GetMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentaryDTO> obtenerCommentById(@PathVariable(value = "publicationId") Long publicationId, @PathVariable(value = "id") Long commentId) {
        CommentaryDTO commentaryDTO = commentaryService.obtenerCommentaryById(publicationId, commentId);
        return new ResponseEntity<>(commentaryDTO, HttpStatus.OK);
    }

    @PostMapping("/publications/{publicationId}/comments")
    public ResponseEntity<CommentaryDTO> guardarCommentary(@PathVariable(value = "publicationId") long publicationId,@Valid @RequestBody CommentaryDTO commentaryDTO) {
        return new ResponseEntity<>(commentaryService.crearCommentary(publicationId, commentaryDTO), HttpStatus.CREATED);
    }

    @PutMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<CommentaryDTO> actualizarCommentary(@PathVariable(value = "publicationId") Long publicationId, @PathVariable(value = "id") Long commentId,@Valid  @RequestBody CommentaryDTO commentaryDTO) {
        CommentaryDTO commentaryActualizado = commentaryService.actualizarCommentary(publicationId, commentId, commentaryDTO);
        return new ResponseEntity<>(commentaryActualizado, HttpStatus.OK);
    }

    @DeleteMapping("/publications/{publicationId}/comments/{id}")
    public ResponseEntity<String> eliminarCommentary(@PathVariable(value = "publicationId") Long publicationId, @PathVariable(value = "id") Long commentId) {
        commentaryService.eliminarCommentary(publicationId, commentId);
        return new ResponseEntity<>("Comentario eliminado con exitos",HttpStatus.OK);
    }

}
