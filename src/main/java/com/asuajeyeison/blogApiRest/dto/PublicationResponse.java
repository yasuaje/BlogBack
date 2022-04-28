package com.asuajeyeison.blogApiRest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicationResponse {


    private List<PublicationDTO> content;
    private int numberPage;
    private int sizePage;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public PublicationResponse() {
    }
}
