package com.uco.inventapp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.inventapp.domain.Client;
import com.uco.inventapp.domain.History;
import com.uco.inventapp.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1")
public class HistoryController {


    @Autowired
    private HistoryService historyService;

    @GetMapping("/history/all")
    public ArrayList<History> get() {
        return historyService.findAll();
    }

    @GetMapping("/history/product")
    public ArrayList<History> getByProduct(@RequestParam(required = true) String product) {

        return historyService.getByProduct(product);
    }

    @GetMapping("/history/date")
    public History getByDate(@RequestParam(required = true) String date) {

        return historyService.getByDate(date);
}

    @PatchMapping(value = "/history/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<History> patch(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(historyService.patch(id, patch), HttpStatus.OK);
    }

}