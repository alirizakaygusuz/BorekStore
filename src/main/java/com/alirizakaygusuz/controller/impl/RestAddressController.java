package com.alirizakaygusuz.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alirizakaygusuz.controller.IRestAddressController;
import com.alirizakaygusuz.controller.RestBaseController;
import com.alirizakaygusuz.controller.response.RootEntity;
import com.alirizakaygusuz.dto.DtoAddress;
import com.alirizakaygusuz.dto.DtoAddressIU;
import com.alirizakaygusuz.service.IAddressService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/rest/api/address")
public class RestAddressController extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @Operation(summary = "Create a new address")
    @PostMapping("/save")
    @Override
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

    @Operation(summary = "Get address by ID")
    @GetMapping("/list/{id}")
    @Override
    public RootEntity<DtoAddress> findAddressById(@PathVariable Long id) {
        return ok(addressService.findAddressById(id));
    }

    @Operation(summary = "Get all addresses")
    @GetMapping("/list")
    @Override
    public RootEntity<List<DtoAddress>> getAllAddresses() {
        return ok(addressService.getAllAddresses());
    }

    @Operation(summary = "Update address by ID")
    @PutMapping("/update/{id}")
    @Override
    public RootEntity<DtoAddress> updateAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU, @PathVariable Long id) {
        return ok(addressService.updateAddress(dtoAddressIU, id));
    }

    @Operation(summary = "Delete address by ID")
    @DeleteMapping("/delete/{id}")
    @Override
    public RootEntity<String> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ok("Address deleted successfully.");
    }
}
