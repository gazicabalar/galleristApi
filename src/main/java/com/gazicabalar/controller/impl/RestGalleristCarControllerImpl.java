package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestGalleristCarController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoGalleristCar;
import com.gazicabalar.dto.DtoGalleristCarIU;
import com.gazicabalar.service.IGalleristCarService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController{

	@Autowired
	private IGalleristCarService galleristCarService;
	
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoGalleristCar> getGalleristCarById(@Valid @PathVariable Long id) {
		return ok(galleristCarService.getGalleristCarById(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoGalleristCar>> getAllGalleristCar() {
		return ok(galleristCarService.getAllGalleristCar());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoGalleristCar> updateGalleristCar(@Valid @PathVariable Long id, @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
		return ok(galleristCarService.updateGalleristCar(id,dtoGalleristCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteGalleristCar(@Valid @PathVariable Long id) {
		return ok(galleristCarService.deleteGalleristCar(id));
	}


}
