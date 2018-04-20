package com.example.vorappServer.controllers;

import com.example.vorappServer.extraClass.DimsHelpClass;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.repo.DimRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paweł on 2018-03-26.
 */

@RestController
@RequestMapping("/dims")
public class DimController {

    @Autowired
    private DimRepo dimRepo;

    @RequestMapping
    public ResponseEntity<Collection<Dimiensions>> findAll(){
        return new ResponseEntity<>(dimRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/dim/id/{id}")
    public ResponseEntity<Dimiensions> findById(@PathVariable("id") Long dim_id){
        Dimiensions dim = dimRepo.findById(dim_id).orElse(null);
        return  new ResponseEntity<>(dim, HttpStatus.OK);
    }

    @PostMapping("/dim/find")
    public  List<Dimiensions> findByDim(@RequestBody DimsHelpClass dimsHelpClass){
        return dimRepo.findByDim(dimsHelpClass.getFirstDimension(), dimsHelpClass.getSecondDimension(),
                dimsHelpClass.getThickness(), dimsHelpClass.getWeight());
    }

    @PostMapping(value = "/createdim")
    public Dimiensions createDim(@RequestBody Dimiensions dimiensions){
        return dimRepo.save(dimiensions);
    }

    @PutMapping(value = "/dim/update/{id}")
    public Dimiensions updateDim(@PathVariable(value = "id") Long dimId, @Valid @RequestBody Dimiensions dim){
        Dimiensions finddim = dimRepo.findById(dimId)
                .orElse(null);

        finddim.setFirstDimension(dim.getFirstDimension());
        finddim.setSecondDimension(dim.getSecondDimension());
        finddim.setThickness(dim.getThickness());
        finddim.setWeight(dim.getWeight());

        return dimRepo.save(finddim);
    }

    @DeleteMapping(value = "/dim/delete/{id}")
    public ResponseEntity<Object> deleteDim(@PathVariable(value = "id") Long dimId){
        Dimiensions dimDelete = dimRepo.findById(dimId).orElse(null);

        dimRepo.delete(dimDelete);

        return ResponseEntity.ok().build();
    }
}
