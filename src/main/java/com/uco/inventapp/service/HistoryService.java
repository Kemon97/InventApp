package com.uco.inventapp.service;

import com.uco.inventapp.domain.History;
import com.uco.inventapp.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
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


}