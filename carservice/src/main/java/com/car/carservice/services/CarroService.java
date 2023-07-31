package com.car.carservice.services;

import com.car.carservice.entities.Carro;
import com.car.carservice.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<Carro> getCarros() {
        return carroRepository.findAll();
    }

    public Carro getCarroById(int id) {
        return carroRepository.findById(id).orElse(null);
    }

    public Carro saveCarro(Carro carro) {
        Carro car1 = carroRepository.save(carro);
        return car1;
    }


    public List<Carro> byUserId(int userId) {
        return carroRepository.findByUserId(userId);
    }
}
