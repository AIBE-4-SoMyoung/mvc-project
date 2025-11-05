package org.example.mvcprojcet.mvc.model.repository;

import org.example.mvcprojcet.mvc.model.entity.Pet;
import java.util.ArrayList;
import java.util.List;

public class InMemoryPetRepository implements PetRepository {
    // 데이터를 저장하거나 불러올 수 있는 체계 -> DB 연결할 수 있는 수단
    private final List<Pet> petList = new ArrayList<>();

    public InMemoryPetRepository() {
        petList.add(new Pet("누렁이", 5, "강아지"));
        petList.add(new Pet("야옹이", 2, "고양이"));
        petList.add(new Pet("짹짹이", 1, "새"));
        petList.add(new Pet("개북이", 10, "거북이"));
        petList.add(new Pet("금붕어", 1, "물고기"));
        petList.add(new Pet("햄스터", 2, "햄스터"));
    }

    // findAll (전체 불러오기)
    @Override
    public List<Pet> findAll() {
        return petList;
    }

    // save (맨 끝에 하나 추가)
    @Override
    public void save(Pet pet) {
        petList.add(pet);
    }
}