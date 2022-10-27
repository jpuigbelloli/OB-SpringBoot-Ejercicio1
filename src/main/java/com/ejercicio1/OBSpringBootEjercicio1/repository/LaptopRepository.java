package com.ejercicio1.OBSpringBootEjercicio1.repository;

import com.ejercicio1.OBSpringBootEjercicio1.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Long> {


}
