package com.backend.backend.controller;

import com.backend.backend.model.Avis;
import com.backend.backend.service.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/clients/{clientId}/avis")
public class AvisController {

    @Autowired
    private AvisService avisService;

    @GetMapping
    public List<Avis> getAllAvis(@PathVariable String clientId) throws ExecutionException, InterruptedException {
        return avisService.getAllAvis(clientId);
    }

    @GetMapping("/{avisId}")
    public Avis getAvisById(@PathVariable String clientId, @PathVariable String avisId) throws ExecutionException, InterruptedException {
        return avisService.getAvisById(clientId, avisId);
    }

    @PostMapping
    public Avis createAvis(@PathVariable String clientId, @RequestBody Avis avis) throws ExecutionException, InterruptedException {
        return avisService.createAvis(clientId, avis);
    }

    @PutMapping("/{avisId}")
    public Avis updateAvis(@PathVariable String clientId, @PathVariable String avisId, @RequestBody Avis avis) throws ExecutionException, InterruptedException {
        return avisService.updateAvis(clientId, avisId, avis);
    }

    @DeleteMapping("/{avisId}")
    public void deleteAvis(@PathVariable String clientId, @PathVariable String avisId) throws ExecutionException, InterruptedException {
        avisService.deleteAvis(clientId, avisId);
    }
}
