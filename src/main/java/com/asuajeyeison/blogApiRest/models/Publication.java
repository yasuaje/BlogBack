package com.asuajeyeison.blogApiRest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "publications", uniqueConstraints = { @UniqueConstraint(columnNames = {"title"})})
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "content", nullable = false)
    private String content;

    @JsonBackReference
    @OneToMany(mappedBy = "publication",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Commentary> comments = new HashSet<>();

    public Publication() {
    }

    public Publication(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }


}