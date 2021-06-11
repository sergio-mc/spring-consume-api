package smc.springconsumeapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/")
public class RandomApiController{

    @Autowired
    private RestTemplate restTemplate;

    private static String url = "https://private-anon-48d3bc8fa9-carsapi1.apiary-mock.com/manufacturers";
    private static String urlEngines = "https://private-anon-48d3bc8fa9-carsapi1.apiary-mock.com/engines";
    private static String urlPokemon = "https://pokeapi.co/api/v2/pokemon/";

    @GetMapping("cars")
    public List<Object> getCars(){
        Object cars = restTemplate.getForObject(url, Object.class);
        return Arrays.asList(cars);
    }

    @GetMapping("engines")
    public List<Object> getEngines(){
        Object engines = restTemplate.getForObject(urlEngines, Object.class);
        return Arrays.asList(engines);
    }

    @GetMapping("pokemon/{id}")
    public List<Object> getPokemonById(@PathVariable("id")String id){
        ResponseEntity<Object> response =
                restTemplate.getForEntity(
                        urlPokemon + id,
                        Object.class);
        Object pokemons = response.getBody();
        return Arrays.asList(pokemons);
    }
}
