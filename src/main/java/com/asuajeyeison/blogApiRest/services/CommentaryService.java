package com.asuajeyeison.blogApiRest.services;

import com.asuajeyeison.blogApiRest.dto.CommentaryDTO;

import java.util.List;

public interface CommentaryService {

    public CommentaryDTO crearCommentary(Long publicationId, CommentaryDTO commentaryDTO);

    public List<CommentaryDTO> obtenerCommentsByPublicationId(Long publicationId);

    public CommentaryDTO obtenerCommentaryById(Long publicationId, Long comentarioId);

    public CommentaryDTO actualizarCommentary(Long publicationId,Long comentarioId, CommentaryDTO solicitudCommentary);

    public void eliminarCommentary(Long publicationId, Long comentarioId);
}
