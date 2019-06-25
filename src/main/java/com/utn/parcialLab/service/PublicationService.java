package com.utn.parcialLab.service;

import com.utn.parcialLab.model.Publication;
import com.utn.parcialLab.model.User;
import com.utn.parcialLab.repository.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class PublicationService {

    @Autowired
    private PublicationRepository publicationRepository;


    public Publication findById(Integer id) {
         Publication p = publicationRepository.findById(id)
                    .orElseThrow( () -> new HttpClientErrorException(HttpStatus.BAD_REQUEST, String.format("No se encontro la publicacion Id nº %s", id)));
            return p;
    }

    public Publication save(Publication p){
        return publicationRepository.save(p);
    }
}
