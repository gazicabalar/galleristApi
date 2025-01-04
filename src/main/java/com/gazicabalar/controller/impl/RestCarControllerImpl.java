package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestCarController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoCar;
import com.gazicabalar.dto.DtoCarIU;
import com.gazicabalar.service.ICarService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/car")
public class RestCarControllerImpl extends RestBaseController implements IRestCarController{

	@Autowired
	private ICarService carService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.saveCar(dtoCarIU));
	}

	@GetMapping("/{id}")
	@Override
	public RootEntity<DtoCar> getCar(@Valid @PathVariable Long id) {
		return ok(carService.getCar(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoCar>> getAllCars() {
		return ok(carService.getAllCars());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoCar> updateCar(@Valid @PathVariable Long id, @Valid @RequestBody DtoCarIU dtoCarIU) {
		return ok(carService.updateCar(id,dtoCarIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteCar(@Valid @PathVariable Long id) {
		return ok(carService.deleteCar(id));
	}

}
