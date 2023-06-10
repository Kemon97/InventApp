package com.uco.inventapp.service;

import com.uco.inventapp.domain.Client;
import com.uco.inventapp.repository.ClientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ArrayList<Client> findAll() {
        return (ArrayList<Client>) clientRepository.findAll();
    }

    @Transactional
    public ArrayList<Client> get(String name) {
        return clientRepository.findByName(name.toUpperCase(Locale.ROOT));
    }

    @Transactional
    public Client getByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Transactional
    public String getLogin(String email,String pass){
        if(clientRepository.countByLogin(email,pass)>0){
            return "123";
        }else{
            return "";
        }

    }

    @Transactional
    public Client save(Client person) {
        if (clientRepository.countByEmail(person.getEmail()) > 0) {
            throw new IllegalArgumentException("email already exits");
        }
        return clientRepository.save(person);
    }

    @Transactional
    public void update(Long id, Client clientDomain) {
        if (clientRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        clientRepository.updateById(clientDomain.getFirstName(), clientDomain.getLastName(), clientDomain.getEmail(), clientDomain.getPassword(), id);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public Client patch(Long id, JsonPatch patch) {
        return clientRepository.save(
                applyPatchToClient(patch, clientRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new)));
    }

    private Client applyPatchToClient(JsonPatch patch, Client person) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(person, JsonNode.class));
            return objectMapper.treeToValue(patched, Client.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
