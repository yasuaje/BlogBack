package com.asuajeyeison.blogApiRest.dto;

import com.asuajeyeison.blogApiRest.models.Commentary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PublicationDTO {
    private Long id;

    @NotEmpty
    @Size(min = 3, message = "El titulo debe tener al menos 3 caracteres")
    private String title;

    @NotEmpty
    @Size(min = 15, message = "El descripcion debe tener al menos 10 caracteres")
    private String description;

    @NotEmpty
    private String content;

    private Set<Commentary> comments;
}
