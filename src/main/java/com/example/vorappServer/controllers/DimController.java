package com.example.vorappServer.controllers;

import com.example.vorappServer.extraClass.DimsHelpClass;
import com.example.vorappServer.model.Dimiensions;
import com.example.vorappServer.repo.DimRepo;
import com.example.vorappServer.repo.SingleOrdersRepo;
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

    private final DimRepo dimRepo;

    private final SingleOrdersRepo singleOrdersRepo;

    @Autowired
    public DimController(DimRepo dimRepo, SingleOrdersRepo singleOrdersRepo) {
        this.dimRepo = dimRepo;
        this.singleOrdersRepo = singleOrdersRepo;
    }

    @RequestMapping
    public ResponseEntity<Collection<Dimiensions>> findAll(){
        return new ResponseEntity<>(dimRepo.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dimiensions> findById(@PathVariable("id") Long dim_id){
        Dimiensions dim = dimRepo.findById(dim_id).orElse(null);
        return  new ResponseEntity<>(dim, HttpStatus.OK);
    }

    @PostMapping("/find")
    public List findByDim(@RequestBody DimsHelpClass dimsHelpClass){
        return dimRepo.findByDim(dimsHelpClass.getFirstDimension(), dimsHelpClass.getSecondDimension(),
                dimsHelpClass.getThickness(), dimsHelpClass.getWeight());
    }

    @PostMapping(value = "/create")
    public Dimiensions createDim(@RequestBody Dimiensions dimiensions){
        return dimRepo.save(dimiensions);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Dimiensions> updateDim(@PathVariable(value = "id") Long dimId, @Valid @RequestBody Dimiensions dim){
        Dimiensions finddim = dimRepo.findById(dimId)
                .orElse(null);


        if(finddim == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        finddim.setFirstDimension(dim.getFirstDimension());
        finddim.setSecondDimension(dim.getSecondDimension());
        finddim.setThickness(dim.getThickness());
        finddim.setWeight(dim.getWeight());

        return new ResponseEntity<>(dimRepo.save(finddim),HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> deleteDim(@PathVariable(value = "id") Long dimId){
        Dimiensions dimDelete = dimRepo.findById(dimId).orElse(null);
        if(dimDelete != null){
            singleOrdersRepo.deleteByDimension(dimDelete);
            dimRepo.delete(dimDelete);
        }
        return ResponseEntity.ok().build();
    }
}
