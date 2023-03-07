package com.uco.inventapp.inventapp.controller;

import com.uco.inventapp.inventapp.domain.Client;
import com.uco.inventapp.inventapp.service.ClientService;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/prueba")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/client")
    public ArrayList<Client> get(@RequestParam(required = true) String first_name) {

        return clientService.get(first_name);
    }

    @GetMapping("/client/email")
    public Client getByEmail(@RequestParam(required = true) String email) {

        return clientService.getByEmail(email);
    }

    @GetMapping("/client/all")
    public ArrayList<Client> get() {
        return clientService.findAll();
    }

    @PostMapping("/client")
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client user) {
        return clientService.save(user);
    }

    @PutMapping("/client/{id}")
    public void update(@PathVariable("id") Long id,
                       @Valid @RequestBody Client Client) {
        clientService.update(id, Client);
    }

    @PatchMapping(value = "/client/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Client> patch(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(clientService.patch(id, patch), HttpStatus.OK);
    }

    @DeleteMapping("/client/{id}")
    public void delete(@PathVariable("id") Long id) {
        clientService.delete(id);
    }
}
