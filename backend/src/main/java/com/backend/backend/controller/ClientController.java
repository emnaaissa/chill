package com.backend.backend.controller;

import com.backend.backend.model.Client;
import com.backend.backend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() throws ExecutionException, InterruptedException {
        return clientService.getAllClients();
    }

    @GetMapping("/{clientId}")
    public Client getClientById(@PathVariable String clientId) throws ExecutionException, InterruptedException {
        return clientService.getClientById(clientId);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) throws ExecutionException, InterruptedException {
        return clientService.createClient(client);
    }

    @PutMapping("/{clientId}")
    public Client updateClient(@PathVariable String clientId, @RequestBody Client client) throws ExecutionException, InterruptedException {
        return clientService.updateClient(clientId, client);
    }

    @DeleteMapping("/{clientId}")
    public void deleteClient(@PathVariable String clientId) throws ExecutionException, InterruptedException {
        clientService.deleteClient(clientId);
    }
}
