 package com.asuajeyeison.blogApiRest.repositories;

import com.asuajeyeison.blogApiRest.models.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
}