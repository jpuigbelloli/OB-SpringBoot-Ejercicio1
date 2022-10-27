package com.ejercicio1.OBSpringBootEjercicio1;

import com.ejercicio1.OBSpringBootEjercicio1.entities.Laptop;
import com.ejercicio1.OBSpringBootEjercicio1.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ObSpringBootEjercicio1Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ObSpringBootEjercicio1Application.class, args);
        System.out.println("Aplicación iniciada correctamente en el puerto 8081");
        LaptopRepository repository = context.getBean(LaptopRepository.class);

        Laptop laptop = new Laptop(null, "Pavilion", "HP", 120000.0, 12, "Ryzen 7");
        Laptop laptop2 = new Laptop(null, "Spectre 360", "HP", 170000.0, 32, "Ryzen 9");

        System.out.println("Numero de Laptops en la base de datos: " + repository.findAll().size());

        repository.save(laptop);
        repository.save(laptop2);

        System.out.println("Numero de Laptops en la base de datos: " + repository.findAll().size());

        //repository.deleteById(1L);

        System.out.println("Numero de Laptops en la base de datos: " + repository.findAll().size());


    }

}
