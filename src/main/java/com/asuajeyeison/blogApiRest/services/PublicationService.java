package com.asuajeyeison.blogApiRest.services;

import com.asuajeyeison.blogApiRest.dto.PublicationDTO;
import com.asuajeyeison.blogApiRest.dto.PublicationResponse;

import java.util.List;

public interface PublicationService {

    public PublicationDTO crearPublicacion(PublicationDTO publicacionDTO);

    public PublicationResponse obtenerAllPublications(int numberPage, int sizePage,String orderBy, String sortDir);

    public PublicationDTO obtenerPublicationById(long id);

    public PublicationDTO actualizarPublication(PublicationDTO publicationDTO, long id);

    public void eliminarPublication(long id);
}
