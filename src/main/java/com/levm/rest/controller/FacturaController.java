package com.levm.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.levm.rest.entity.Factura;
import com.levm.rest.repository.FacturaRepository;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

	@Autowired
	private FacturaRepository repositorio;

	@GetMapping
	public List<Factura> buscarTodas() {
		return repositorio.buscarTodas();
	}
	@GetMapping("/{numero}")
	public Factura buscarUna(@PathVariable int numero)
	{
		return repositorio.buscarUna(numero);
	}
	@DeleteMapping("/{numero}")
	public void eliminarUna(Factura factura) {
		repositorio.eliminarUna(factura);
	}
	@PostMapping()
	public void agregarUna(@RequestBody Factura factura) {
		repositorio.agregarUna(factura);
	}
	@PutMapping("/{numero}")
	public void actualizarUna(@RequestBody Factura factura) {
		repositorio.actualizarUna(factura);
	}
	
	
	
	
	
	
}
