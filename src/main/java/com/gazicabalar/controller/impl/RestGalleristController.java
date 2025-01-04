package com.gazicabalar.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.gazicabalar.controller.IRestGalleristController;
import com.gazicabalar.controller.RestBaseController;
import com.gazicabalar.controller.RootEntity;
import com.gazicabalar.dto.DtoGallerist;
import com.gazicabalar.dto.DtoGalleristIU;
import com.gazicabalar.service.IGalleristService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController {

	@Autowired
	private IGalleristService galleristService;
	
	@PostMapping("/save")
	@Override
	public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.saveGallerist(dtoGalleristIU));
	}

	@GetMapping("/get/{id}")
	@Override
	public RootEntity<DtoGallerist> getGallerist(@Valid @PathVariable Long id) {
		return ok(galleristService.getGallerist(id));
	}

	@GetMapping("/all")
	@Override
	public RootEntity<List<DtoGallerist>> getAllGallerist() {
		return ok(galleristService.getAllGallerist());
	}

	@PostMapping("/update/{id}")
	@Override
	public RootEntity<DtoGallerist> updateGallerist(@Valid @PathVariable Long id, @RequestBody DtoGalleristIU dtoGalleristIU) {
		return ok(galleristService.updateGallerist(id,dtoGalleristIU));
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public RootEntity<String> deleteGallerist(@Valid @PathVariable Long id) {
		return ok(galleristService.deleteGallerist(id));
	}

}
