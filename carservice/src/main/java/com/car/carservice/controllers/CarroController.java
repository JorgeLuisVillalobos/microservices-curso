package com.car.carservice.controllers;

import com.car.carservice.entities.Carro;
import com.car.carservice.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarroController {
    @Autowired
    private CarroService carroService;

    @GetMapping
    public ResponseEntity<List<Carro>> carroList() {
        List<Carro> carroList = carroService.getCarros();
        if(carroList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carroList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarro(@PathVariable("id") int id) {
        Carro carro = carroService.getCarroById(id);
        if(carro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro) {
        Carro carro1 = carroService.saveCarro(carro);
        return ResponseEntity.ok(carro1);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Carro>> listCarrosByUser(@PathVariable("userId") int userId) {
        List<Carro> carroList = carroService.byUserId(userId);
        if(carroList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(carroList);
    }

}
