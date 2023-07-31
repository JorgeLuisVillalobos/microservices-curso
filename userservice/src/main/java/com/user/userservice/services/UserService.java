package com.user.userservice.services;

import com.user.userservice.entities.User;
import com.user.userservice.feignclients.CarroFeignClient;
import com.user.userservice.models.Car;
import com.user.userservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;

    // Método con RestTemplate
    public List<Car> getCars(int userId) {
        List<Car> carList = restTemplate.getForObject("http://localhost:8002/cars/user/" + userId, List.class);
        return carList;
    }

    public Car saveCar(int userId, Car carro) {
        carro.setUserId(userId);
        Car carro1 = carroFeignClient.save(carro);
        return carro1;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        User us1 = userRepository.save(user);
        return us1;
    }

    public Map<String, Object> getUserAndCars(int userId) {
        Map<String, Object> map = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null) {
            map.put("message", "User doesn't exists");
        }
        else {
            map.put("User", user);
        }

        List<Car> carList = carroFeignClient.getCarsByUserId(userId);
        if(carList.isEmpty()) {
            map.put("message", "User has not cars");
        }
        else
            map.put("Cars", carList);
        return map;
    }

}
