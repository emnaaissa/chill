package com.backend.backend.controller;

import com.backend.backend.service.FirestoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/firebase")
public class TestController {

    @Autowired
    private FirestoreService firestoreService;

    // Endpoint to add data to Firestore
    @PostMapping("/addData")
    public String addData(@RequestParam String documentId, @RequestParam String name, @RequestParam String value) {
        try {
            return firestoreService.addData(documentId, name, value);
        } catch (ExecutionException | InterruptedException e) {
            return "Error adding data: " + e.getMessage();
        }
    }

    // Endpoint to retrieve data from Firestore
    @GetMapping("/getData")
    public Map<String, Object> getData(@RequestParam String documentId) {
        try {
            return firestoreService.getData(documentId);
        } catch (ExecutionException | InterruptedException e) {
            return Map.of("error", e.getMessage());
        }
    }
}
