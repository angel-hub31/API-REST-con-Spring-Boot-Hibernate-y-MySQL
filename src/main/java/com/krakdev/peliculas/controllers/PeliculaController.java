package com.krakdev.peliculas.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
	
	private final ServicioPelicula servicio;
	
	//constructor
	public PeliculaController(ServicioPelicula servicio) {
		this.servicio = servicio;
	}
	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Pelicula pelicula) {

		try {
			Pelicula creado = servicio.crear(pelicula);
			return ResponseEntity.status(HttpStatus.CREATED).body(creado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear pelicula");

		}

	}
	
	@GetMapping
	public ResponseEntity<?> listar() {

		try {
			List<Pelicula> peliculas = servicio.listar();
			return ResponseEntity.ok(peliculas);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar peliculas");

		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {

		try {
			Pelicula pelicula = servicio.buscarPorId(id);

			if (pelicula == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("la pelicula con la Id : " + id + "  no fue encontrado");

			}
			return ResponseEntity.ok(pelicula);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar pelicula");

		}

	}
	
	
	@GetMapping("/genero")
	public ResponseEntity<?> buscarPorGenero(@RequestParam String genero){
		
		try {
			List<Pelicula> peliculas =servicio.buscarPorGenero(genero);
			return ResponseEntity.ofNullable(peliculas);
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar por genero");

			
		}
		
		
	}
		
		@GetMapping("/disponible")
		public ResponseEntity<?> buscarPorDisponible(@RequestParam boolean disponible){
			
			try {
				List<Pelicula> peliculas =servicio.buscarPorDisponible(disponible);
				return ResponseEntity.ofNullable(peliculas);
				
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar disponibles");

				
			
			
		}
		
	}
	
		@PutMapping("/{id}")
		public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula){
			try {
				Pelicula actualizado =servicio.actualizar(id, pelicula);
				if(actualizado == null) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("La Pelicula con la Id : " + id + "  no fue encontrado");

					
				}
				return ResponseEntity.ok(actualizado);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar");

			}
			
		}
	
		@DeleteMapping("/{id}")
		public ResponseEntity<?> eliminar(@PathVariable Long id){
			try {
				
				boolean eliminado=servicio.eliminar(id);
				
				if(!eliminado) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("El pelicula con la Id : " + id + "  no fue encontrado");

					
				}
				return ResponseEntity.ok("Pelicula eliminado con exito");
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar");

				
			}
			
			
		}

}
