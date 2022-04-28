package com.asuajeyeison.blogApiRest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorDetalles {
    private Date marcaDeTiemmpo;
    private String mensaje;
    private String detalles;

    public ErrorDetalles(Date marcaDeTiemmpo, String mensaje, String detalles) {
        this.marcaDeTiemmpo = marcaDeTiemmpo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
}
