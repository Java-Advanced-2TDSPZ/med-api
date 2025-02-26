package br.com.fiap.med_api.controller;

import java.util.ArrayList;
import java.util.List;
import br.com.fiap.med_api.model.Category;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController //component

public class CategoryController {

   private List<Category> repository = new ArrayList<>();

    //Listar todas as categorias
    //GET :8080/categories -> 200 ok -> JSON
    @GetMapping("/categories")
    public List<Category> index() {
       return repository;
    }




    //Cadastrar uma categoria
    @PostMapping("/categories")
    //@ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Category> create(@RequestBody Category category){
        System.out.println("Cadastrando..." + category.getName());
        repository.add(category);
        return ResponseEntity.status(201).body(category);
    }

    //Detalhes da categorias
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> get(@PathVariable Long id){
        System.out.println("Buscando categoria " + id);
        var category = repository
            .stream()
            .filter(c -> c.getId().equals(id))
            .findFirst();

        if (category.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(category.get());
    }

    //Apagar uma categoria
    
}
