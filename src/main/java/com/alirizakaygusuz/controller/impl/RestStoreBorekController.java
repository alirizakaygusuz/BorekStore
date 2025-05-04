package com.alirizakaygusuz.controller.impl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alirizakaygusuz.controller.IRestStoreBorekController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoStoreBorek;
import com.alirizakaygusuz.dto.DtoStoreBorekIU;
import com.alirizakaygusuz.service.IStoreBorekService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/api/storeborek")
public class RestStoreBorekController extends RestBaseController implements IRestStoreBorekController {

    @Autowired
    private IStoreBorekService storeBorekService;

    @Operation(summary = "Assign a börek to a store")
    @PostMapping("/save")
    @Override
    public RootEntity<DtoStoreBorek> saveStoreBorek(@Valid @RequestBody DtoStoreBorekIU dtoStoreBorekIU) {
        return ok(storeBorekService.saveStoreBorek(dtoStoreBorekIU));
    }

    @Operation(summary = "Get store-börek relation by ID")
    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoStoreBorek> findStoreBorekById(@PathVariable Long id) {
        return ok(storeBorekService.findStoreBorekById(id));
    }

    @Operation(summary = "Get all store-börek relations")
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoStoreBorek>> getAllStoreBoreks() {
        return ok(storeBorekService.getAllStoreBoreks());
    }

    @Operation(summary = "Update store-börek relation by ID")
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoStoreBorek> updateStoreBorek(@Valid @RequestBody DtoStoreBorekIU dtoStoreBorekIU, @PathVariable Long id) {
        return ok(storeBorekService.updateStoreBorek(dtoStoreBorekIU, id));
    }

    @Operation(summary = "Delete store-börek relation by ID")
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteStoreBorek(@PathVariable Long id) {
        storeBorekService.deleteStoreBorek(id);
        return ok("StoreBorek has been deleted.");
    }
}
