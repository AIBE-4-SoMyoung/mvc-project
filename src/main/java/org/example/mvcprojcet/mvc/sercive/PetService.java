package org.example.mvcprojcet.mvc.sercive;

import org.example.mvcprojcet.mvc.model.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> readAll();
    void create(String name, int age, String category);
}