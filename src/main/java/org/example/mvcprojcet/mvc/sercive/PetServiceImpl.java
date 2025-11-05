package org.example.mvcprojcet.mvc.sercive;

import org.example.mvcprojcet.mvc.model.entity.Pet;
import org.example.mvcprojcet.mvc.model.repository.InMemoryPetRepository;
import org.example.mvcprojcet.mvc.model.repository.PetRepository;
import org.example.mvcprojcet.mvc.model.repository.SupabasePetRepository;

import java.util.List;

// type1 : IPetService (I) - PetService (C)
// type2 : PetService (I) - PetServiceImpl (C)
// -> 나중에 큰 변경이 구현체(Class)에서 있어도 서로 참조관계가 꼬이지 않게 하려고...
// type3 : PetService (C)
public class PetServiceImpl implements PetService {
    //    private final PetRepository repository = new InMemoryPetRepository();
    private final PetRepository repository = new SupabasePetRepository(); // 방법 1

    @Override
    public List<Pet> readAll() {
        // 변경 로직...
        return repository.findAll(); // Controller는 최종적인 결과만.
    }

    @Override
    public void create(String name, int age, String category) {
        // Controller? -> age => int
        Pet pet = new Pet(name, age, category);
        repository.save(pet);
    }
}