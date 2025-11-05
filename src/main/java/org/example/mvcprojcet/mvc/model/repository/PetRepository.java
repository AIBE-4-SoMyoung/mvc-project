package org.example.mvcprojcet.mvc.model.repository;

import org.example.mvcprojcet.mvc.model.entity.Pet;
import java.util.List;

public interface PetRepository {
    List<Pet> findAll();
    void save(Pet pet);
}
