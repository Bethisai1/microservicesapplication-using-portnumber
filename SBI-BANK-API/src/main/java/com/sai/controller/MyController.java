package com.sai.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.sai.entity.RequiredResponse;
import com.sai.entity.bankapplication;
import com.sai.model.bankapplications;
import com.sai.server.Bankservice;

@RestController
@RequestMapping("/sbi")
public class MyController {

    @Autowired
    private Bankservice bank;

    @PostMapping("/save")
    public ResponseEntity<String> banksave(@RequestBody bankapplications bankApplication) {
        bankapplications savedApplication = bank.banksave(bankApplication);
        if (savedApplication != null) {
            return ResponseEntity.ok("Data saved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data not saved");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<bankapplications> findById(@PathVariable int id) {
        bankapplications foundRecord = bank.findById(id);
        if (foundRecord != null) {
            return ResponseEntity.ok(foundRecord);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getData/{id}")
    public ResponseEntity<RequiredResponse> getData(@PathVariable int id) {
        RequiredResponse requiredResponse = new RequiredResponse();

        // This is for Bank Center Details
        int center = bank.findById(id).getId();
        requiredResponse.setCenter(center);

        // Then Get citizens registered to Bank Details
        try {
            // Assuming the URL is correct and the service is accessible
            ResponseEntity<bankapplication[]> responseEntity = restTemplate.getForEntity("http://localhost:8000/sbi/getData/" + id, bankapplication[].class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                List<bankapplication> listOfCitizens = Arrays.asList(responseEntity.getBody());
                requiredResponse.setCitizens(listOfCitizens);
                return ResponseEntity.ok(requiredResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } catch (HttpStatusCodeException ex) {
            // Handle specific HTTP status code exceptions
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.notFound().build();
            } else {
                // Handle other HTTP status code exceptions
                return ResponseEntity.status(ex.getStatusCode()).body(null);
            }
        } catch (Exception e) {
            // Handle generic exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    }
