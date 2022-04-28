package com.asuajeyeison.blogApiRest.repositories.controllers;

import com.asuajeyeison.blogApiRest.dto.PublicationDTO;
import com.asuajeyeison.blogApiRest.dto.PublicationResponse;
import com.asuajeyeison.blogApiRest.services.PublicationService;
import com.asuajeyeison.blogApiRest.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/publications")
public class PublicationController {

    @Autowired
    private PublicationService publicationService;

    @GetMapping
    public PublicationResponse listarPublication(
            @RequestParam(value = "PageNo", defaultValue = AppConstants.PAGE_NUMBER_DEFAULT, required = false) int numberPage,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_DEFAULT, required = false) int sizePage,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY_DEFAULT,required = false) String orderBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.ORDER_ADDRESS_DEFAULT,required = false) String sortDir) {
        return publicationService.obtenerAllPublications(numberPage, sizePage, orderBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> obtenerPublicationById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(publicationService.obtenerPublicationById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicationDTO> guardarPublication(@Valid @RequestBody PublicationDTO publicationDTO) {
        return new ResponseEntity<>(publicationService.crearPublicacion(publicationDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> actualizarPublication(@Valid @RequestBody PublicationDTO publicationDTO, @PathVariable(name = "id") long id) {
        PublicationDTO publicationResponse = publicationService.actualizarPublication(publicationDTO, id);
        return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPublication(@PathVariable(name = "id") long id) {
        publicationService.eliminarPublication(id);
        return new ResponseEntity<>("Publicacion eliminada con Exito", HttpStatus.OK);
    }
}
