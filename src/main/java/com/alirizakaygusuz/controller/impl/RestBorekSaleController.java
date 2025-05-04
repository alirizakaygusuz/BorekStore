package com.alirizakaygusuz.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alirizakaygusuz.controller.IBorekSaleController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoBorekSale;
import com.alirizakaygusuz.dto.DtoBorekSaleIU;
import com.alirizakaygusuz.service.IBorekSaleService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/boreksale")
public class RestBorekSaleController extends RestBaseController implements IBorekSaleController {

    @Autowired
    private IBorekSaleService borekSaleService;

    @Operation(summary = "Create a new börek sale")
    @PostMapping("/save")
    @Override
    public RootEntity<DtoBorekSale> saveBorekSale(@Valid @RequestBody DtoBorekSaleIU dtoBorekSaleIU) {
        return ok(borekSaleService.saveBorekSale(dtoBorekSaleIU));
    }

    @Operation(summary = "Get börek sale by ID")
    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoBorekSale> getBorekSaleById(@PathVariable Long id) {
        return ok(borekSaleService.getBorekSaleById(id));
    }

    @Operation(summary = "Get all börek sales")
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoBorekSale>> getBorekSales() {
        return ok(borekSaleService.getBorekSales());
    }

    @Operation(summary = "Update börek sale by ID")
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoBorekSale> updateBorekSale(@Valid @RequestBody DtoBorekSaleIU dtoBorekSaleIU, @PathVariable Long id) {
        return ok(borekSaleService.updateBorekSale(dtoBorekSaleIU, id));
    }

    @Operation(summary = "Delete börek sale by ID")
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteBorekSale(@PathVariable Long id) {
        borekSaleService.deleteBorekSale(id);
        return ok("Borek sale has been successfully deleted");
    }
}
