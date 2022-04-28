package com.asuajeyeison.blogApiRest.repositories;

import com.asuajeyeison.blogApiRest.models.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    public List<Commentary> findByPublicationId(long publicationId);
}