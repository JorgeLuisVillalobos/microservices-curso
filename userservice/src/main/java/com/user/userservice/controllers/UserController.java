package com.user.userservice.controllers;

import com.user.userservice.entities.User;
import com.user.userservice.models.Car;
import com.user.userservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> userList() {
        List<User> users = userService.getUsers();
        if(users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        if(user == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    // Consume el microservicio de carros
    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCarsOfUser(@PathVariable("userId") int userId) {
        User user = userService.getUserById(userId);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        List<Car> carList = userService.getCars(userId);
        if(carList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carList);
    }

    // Guardar un car con su userId utilizando el microservicio FeignClient
    @PostMapping("/car/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
        Car car1 = userService.saveCar(userId, car);
        return ResponseEntity.ok(car1);
    }

    // Obtiene al usuario y sus carros
    @GetMapping("/all/{userId}")
    public ResponseEntity<Map<String, Object>> getUserAndCars(@PathVariable("userId") int userId) {
        Map<String, Object> map = userService.getUserAndCars(userId);
        return ResponseEntity.ok(map);
    }
}
