package com.asuajeyeison.blogApiRest.services.serviceImpl;

import com.asuajeyeison.blogApiRest.dto.PublicationDTO;
import com.asuajeyeison.blogApiRest.dto.PublicationResponse;
import com.asuajeyeison.blogApiRest.exceptions.ResourceNotFoundException;
import com.asuajeyeison.blogApiRest.models.Publication;
import com.asuajeyeison.blogApiRest.repositories.PublicationRepository;
import com.asuajeyeison.blogApiRest.services.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationServiceImpl implements PublicationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicationRepository publicationRepository;

    @Override
    public PublicationDTO crearPublicacion(PublicationDTO publicationDTO) {

        //Convertimos de DTO a entidad
        Publication publication = mapearEntidad(publicationDTO);
        Publication newPublication = publicationRepository.save(publication);

        //Convertimos de entidad a DTO
        PublicationDTO publicationResponse = mapearDTO(newPublication);
        return publicationResponse;
    }

    @Override
    public PublicationResponse obtenerAllPublications(int numberPage, int sizePage,String orderBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(orderBy).ascending():Sort.by(orderBy).descending();
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        Page<Publication> publications = publicationRepository.findAll(pageable);

        List<Publication> listPublications = publications.getContent();
        List<PublicationDTO> content = listPublications.stream().map(publication -> mapearDTO(publication)).collect(Collectors.toList());

        PublicationResponse publicationResponse = new PublicationResponse();
        publicationResponse.setContent(content);
        publicationResponse.setNumberPage(publications.getNumber());
        publicationResponse.setSizePage(publications.getSize());
        publicationResponse.setTotalElements(publications.getTotalElements());
        publicationResponse.setTotalPages(publications.getTotalPages());
        publicationResponse.setLast(publications.isLast());

        return publicationResponse;
    }

    @Override
    public PublicationDTO obtenerPublicationById(long id) {
        Publication publication = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication","id",id));
        return mapearDTO(publication);
    }

    @Override
    public PublicationDTO actualizarPublication(PublicationDTO publicationDTO, long id) {
        Publication publication = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication","id",id));

        publication.setTitle(publicationDTO.getTitle());
        publication.setDescription(publicationDTO.getDescription());
        publication.setContent(publicationDTO.getContent());

        Publication publicationActualizada = publicationRepository.save(publication);
        return mapearDTO(publicationActualizada);

    }

    @Override
    public void eliminarPublication(long id) {
        Publication publication = publicationRepository
                .findById(id).orElseThrow(() -> new ResourceNotFoundException("Publication","id",id));
        publicationRepository.delete(publication);
    }

    //Convierte entidad a DTO
    private PublicationDTO mapearDTO(Publication publication){
        PublicationDTO publicationDTO= modelMapper.map(publication,PublicationDTO.class);
        return publicationDTO;
    }

    //Convierte DTO a entidad
    private Publication mapearEntidad(PublicationDTO publicationDTO){
        Publication publication = modelMapper.map(publicationDTO, Publication.class);
        return publication;
    }

}
