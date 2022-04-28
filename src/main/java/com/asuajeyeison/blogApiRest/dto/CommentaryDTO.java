package com.asuajeyeison.blogApiRest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CommentaryDTO {
    private Long id;

    @NotEmpty(message = "El nombre no debe estar vacio")
    private String name;

    @NotEmpty(message = "El email no debe estar vacio")
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10,message = "El cuerpo del comentario  debe tener al menos 10 caracteres")
    private String body;
}
