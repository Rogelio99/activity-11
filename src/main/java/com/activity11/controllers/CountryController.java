package com.activity11.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.activity11.dao.CountryDAO;
import com.activity11.models.Country;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Paises")
public class CountryController {
    @Autowired
    private CountryDAO countryDAO;

	@GetMapping(value ="", params = "nombre")
    public ResponseEntity<List<Country>> getFilterByName(@RequestParam String nombre){
        try {
            List<Country> countries = countryDAO.findByName(nombre);
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Country> create(@RequestBody Country country) {
        try {
            Country countryData = countryDAO.save(country);
            return new ResponseEntity<>(countryData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Country>> getAll(){
        try {
            List<Country> countries = countryDAO.findAll();
            return new ResponseEntity<>(countries, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Country> getById(@PathVariable(value="id") String id){
        try {
            Optional<Country> country = countryDAO.findById(Integer.parseInt(id));
            Country countryData = country.get();
            return new ResponseEntity<>(countryData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Country> update(@RequestBody Country country, @PathVariable(value="id") String id){
        try {
            Optional<Country> countryData = countryDAO.findById(Integer.parseInt(id));
            if (countryData.isPresent()) {
                Country countryUpdate = countryData.get();
                countryUpdate.setName(country.getName());
                countryUpdate.setStatus(country.getStatus());
                Country countryUpdated = countryDAO.save(countryUpdate);
                return new ResponseEntity<>(countryUpdated, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Country> changeStatus(@PathVariable(value="id") String id){
        try {
            Optional<Country> countryData = countryDAO.findById(Integer.parseInt(id));
            if (countryData.isPresent()) {
                Country countryUpdate = countryData.get();
                countryUpdate.setStatus(!countryUpdate.getStatus());
                Country countryUpdated = countryDAO.save(countryUpdate);
                return new ResponseEntity<>(countryUpdated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Country> delete(@PathVariable(value="id") String id){
        try {
            Optional<Country> countryData = countryDAO.findById(Integer.parseInt(id));
            if(countryData.isPresent()){
                Country country = countryData.get();
                countryDAO.delete(country);
                return new ResponseEntity<>(country, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
