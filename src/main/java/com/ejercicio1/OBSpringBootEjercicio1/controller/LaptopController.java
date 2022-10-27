package com.ejercicio1.OBSpringBootEjercicio1.controller;

import com.ejercicio1.OBSpringBootEjercicio1.entities.Laptop;
import com.ejercicio1.OBSpringBootEjercicio1.repository.LaptopRepository;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private LaptopRepository laptopRepository;
    private List<Laptop> laptops;
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }


    // http://localhost:8081/api/laptops

    // Buscar todas las laptops que hay en la base de datos
    @GetMapping("/api/laptops")
    public List<Laptop> findAll() {
        return laptopRepository.findAll();
    }

    // Buscar una laptop por ID en la base de datos
    @GetMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> findOneByID(@PathVariable Long id) {

        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        // OPCION 1
        if (laptopOpt.isPresent()) {
            return ResponseEntity.ok(laptopOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
        /* OPCION 2
        return laptopOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        */
        /* OPCION 3
        return laptopOpt.orElse(null);
        */
    }

    //crear una nueva laptop
    @PostMapping("/api/laptops")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers) {
        System.out.println(headers.get("User-Agent"));
        if(laptop.getId() != null ){ //quiere decir que existe el id y por tanto no es una creacion
            log.warn("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }


    //actualizar una laptop en base de datos
    @PutMapping ("/api/laptops")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if(laptop.getId() == null) { //si no tiene ID quiere decir que es una creacion
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())) {
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        // aqui se realiza el proceso de actualizacion
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    //borrar una laptop en base de datos
    @DeleteMapping("/api/laptops/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepository.existsById(id)) {
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptops")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request for deleting all laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
