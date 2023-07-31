package com.user.userservice.feignclients;

import com.user.userservice.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "carservice", url = "http://localhost:8002", path = "/cars")
//@RequestMapping("/car")
public interface CarroFeignClient {

    @PostMapping()
    public Car save(@RequestBody Car carro);

    @GetMapping("/user/{userId}")
    public List<Car> getCarsByUserId(@PathVariable("userId") int userId);
}
