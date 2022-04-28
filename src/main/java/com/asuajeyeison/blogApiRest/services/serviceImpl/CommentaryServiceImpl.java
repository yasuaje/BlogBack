package com.asuajeyeison.blogApiRest.services.serviceImpl;

import com.asuajeyeison.blogApiRest.dto.CommentaryDTO;
import com.asuajeyeison.blogApiRest.exceptions.BlogAppException;
import com.asuajeyeison.blogApiRest.exceptions.ResourceNotFoundException;
import com.asuajeyeison.blogApiRest.models.Commentary;
import com.asuajeyeison.blogApiRest.models.Publication;
import com.asuajeyeison.blogApiRest.repositories.CommentaryRepository;
import com.asuajeyeison.blogApiRest.repositories.PublicationRepository;
import com.asuajeyeison.blogApiRest.services.CommentaryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryServiceImpl implements CommentaryService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public CommentaryDTO crearCommentary(Long publicationId, CommentaryDTO commentaryDTO) {
        Commentary commentary = mapearEntidad(commentaryDTO);
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication","id",publicationId));

        commentary.setPublication(publication);
        Commentary newCommentary = commentaryRepository.save(commentary);
        return mapearDTO(newCommentary);
    }

    @Override
    public List<CommentaryDTO> obtenerCommentsByPublicationId(Long publicationId) {
        List<Commentary> comments = commentaryRepository.findByPublicationId(publicationId);
        return comments.stream().map(commentary -> mapearDTO(commentary)).collect(Collectors.toList());
    }

    @Override
    public CommentaryDTO obtenerCommentaryById(Long publicationId, Long comentarioId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication","id",publicationId));
        Commentary commentary = commentaryRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentary","id",comentarioId));

        if(!commentary.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"El comentarion no pertenece a la publicacion");
        }
        return mapearDTO(commentary);
    }

    @Override
    public CommentaryDTO actualizarCommentary(Long publicationId,Long comentarioId, CommentaryDTO solicitudCommentary) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication","id",publicationId));
        Commentary commentary = commentaryRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentary","id",comentarioId));

        if(!commentary.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"El comentarion no pertenece a la publicacion");
        }
        commentary.setName(solicitudCommentary.getName());
        commentary.setEmail(solicitudCommentary.getEmail());
        commentary.setBody(solicitudCommentary.getBody());

        Commentary commentaryUpdate = commentaryRepository.save(commentary);
        return mapearDTO(commentaryUpdate);
    }

    @Override
    public void eliminarCommentary(Long publicationId, Long comentarioId) {
        Publication publication = publicationRepository.findById(publicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Publication","id",publicationId));
        Commentary commentary = commentaryRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Commentary","id",comentarioId));

        if(!commentary.getPublication().getId().equals(publication.getId())){
            throw new BlogAppException(HttpStatus.BAD_REQUEST,"El comentarion no pertenece a la publicacion");
        }
        commentaryRepository.delete(commentary);
    }

    private CommentaryDTO mapearDTO(Commentary commentary){
        CommentaryDTO commentaryDTO = modelMapper.map(commentary,CommentaryDTO.class);
        return commentaryDTO;
    }

    private Commentary mapearEntidad(CommentaryDTO commentaryDTO){
        Commentary commentary = modelMapper.map(commentaryDTO,Commentary.class);
        return commentary;
    }

}
