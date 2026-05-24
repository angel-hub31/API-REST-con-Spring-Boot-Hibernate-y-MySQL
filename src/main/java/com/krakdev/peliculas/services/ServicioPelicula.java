package com.krakdev.peliculas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.repository.PeliculaRepository;
@Service
public class ServicioPelicula {
	
	private final PeliculaRepository repository;
//generamos constructor que invoca a la clase PeliculaRepository  y que recibe un parametro
	public ServicioPelicula(PeliculaRepository repository) {
	
		this.repository = repository;
	}
	
	public Pelicula crear(Pelicula pelicula) {
		return repository.save(pelicula);

	}
	
	public List<Pelicula> listar() {
		return repository.findAll();
	}
	
	public Pelicula buscarPorId(Long id) {
		Optional<Pelicula> resultado = repository.findById(id);
		return resultado.orElse(null);
	}
	
	public Pelicula actualizar(Long id, Pelicula peliculaActualizado) {
		Pelicula pelicula = buscarPorId(id);

		if (pelicula == null) {
			return null;

		}
		
		//seteamos 
		
		pelicula.setNombre(peliculaActualizado.getNombre());
		pelicula.setDirector(peliculaActualizado.getDirector());
		pelicula.setGenero(peliculaActualizado.getGenero());
		pelicula.setDuracion(peliculaActualizado.getDuracion());
		pelicula.setDisponible(peliculaActualizado.isDisponible());
		
		return repository.save(pelicula);

	
	}
	public boolean eliminar(Long id) {
		Pelicula pelicula = buscarPorId(id);

		if (pelicula == null) {
			return false;
			

		}
		repository.deleteById(id);
		return true;
		
	}
	public List<Pelicula> buscarPorGenero(String genero){
		return repository.findByGenero(genero);
		
	}
	public List<Pelicula> buscarPorDisponible(boolean disponible){
		return repository.findByDisponible(disponible);
		
	}

	
	
	

}
