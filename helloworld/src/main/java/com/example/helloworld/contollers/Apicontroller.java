package com.example.helloworld.contollers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.domain.producto;

@RestController
public class Apicontroller {

    @GetMapping("/") //
    public String home(){
        return"home de campers";
    }
    
    @GetMapping("/saludo")
    public String saludo(
     @RequestParam(name = "nombre", required = true) String name,
     @RequestParam(name = "apellido",required = false, defaultValue ="apellido comun")String lastname){
        return"hello " + name +" "+ lastname;
    }



    
    @GetMapping("/search")
    public Map<String,String> buscar(
     @RequestParam(name = "name", defaultValue = "") String name
     ){

        Map<String, String> cities = new HashMap<>();
        cities.put("BUC", "Bucaramanga");
        cities.put("NYC", "New York");
        cities.put("BOG", "Bogota");
        cities.put("NVA", "Neiva");
        cities.put("LET", "Leticia");
        cities.put("PER", "Pereira");

        if(cities.containsKey(name)){
            return Map.of(name, cities.get(name));
        }else{
            return cities;
        }
    }


        
    @GetMapping("/tax")
    public Map<String,Object> calcular(
     @RequestParam(defaultValue = "0") double impuestos
     ){
        List<producto> productos = new ArrayList<>(List.of(new producto(1,"pan",3000)));
        productos.add(new producto(2, "gaseosa", 5000));
        productos.add(new producto(3, "salchicha zenu", 3000));

        double total=0;
        for(int i=0; i<productos.size();++i){
        
         double sumar = productos.get(i).getPrice();
         total += sumar;

        }

         impuestos=impuestos/100;

         double total_impuesto = impuestos * total+total;

        
      
      return Map.of("productos",productos,"total",total_impuesto,"valor_neto",total);

    }



    
}
