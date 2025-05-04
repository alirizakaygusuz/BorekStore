package com.alirizakaygusuz.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alirizakaygusuz.controller.IRestBorekController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorek;
import com.alirizakaygusuz.dto.DtoBorekIU;
import com.alirizakaygusuz.service.IBorekService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/borek")
public class RestBorekController extends RestBaseController implements IRestBorekController {

    @Autowired
    private IBorekService borekService;

    @Operation(summary = "Create a new börek")
    @PostMapping("/save")
    @Override
    public RootEntity<DtoBorek> saveBorek(@Valid @RequestBody DtoBorekIU dtoBorekIU) {
        return ok(borekService.saveBorek(dtoBorekIU));
    }

    @Operation(summary = "Get börek by ID")
    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoBorek> findBorekById(@Valid @PathVariable Long id) {
        return ok(borekService.findBorekById(id));
    }

    @Operation(summary = "Get all böreks")
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoBorek>> getAllBoreks() {
        return ok(borekService.getAllBoreks());
    }

    @Operation(summary = "Update börek by ID")
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoBorek> updateBorek(@Valid @RequestBody DtoBorekIU dtoBorekIU, @Valid @PathVariable Long id) {
        return ok(borekService.updateBorek(dtoBorekIU, id));
    }

    @Operation(summary = "Delete börek by ID")
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteBorek(@Valid @PathVariable Long id) {
        borekService.deleteBorek(id);
        return ok("Borek has been deleted successfully");
    }
}
