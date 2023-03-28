package com.uco.inventapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.inventapp.domain.History;
import com.uco.inventapp.repository.HistoryRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository historyRepository;


    @Transactional
    public ArrayList<History> findAll() {
        return (ArrayList<History>) historyRepository.findAll();
    }

    @Transactional
    public ArrayList<History> getByProduct(String product) {
        return historyRepository.findByProduct(product);
    }


    @Transactional
    public History getByDate(String date) {
        return historyRepository.findByDate(date);
    }

    @Transactional
    public History patch(Long id, JsonPatch patch) {
        return historyRepository.save(
                applyPatchToClient(patch, historyRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new)));
    }

    private History applyPatchToClient(JsonPatch patch, History historial) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(historial, JsonNode.class));
            return objectMapper.treeToValue(patched, History.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}