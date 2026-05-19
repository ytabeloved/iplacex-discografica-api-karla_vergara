package org.iplacex.proyectos.discografia.artistas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    // CREAR ARTISTA
    @PostMapping(
        value = "/artista",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        try {
            Artista nuevo = artistaRepository.save(artista);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear artista", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // OBTENER TODOS
    @GetMapping(
        value = "/artistas",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Artista>> HandleGetAristasRequest() {
        return new ResponseEntity<>(artistaRepository.findAll(), HttpStatus.OK);
    }

    // OBTENER POR ID
    @GetMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleGetArtistaRequest(@PathVariable String id) {
        if (artistaRepository.existsById(id)) {
            return new ResponseEntity<>(artistaRepository.findById(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
    }

    // ACTUALIZAR
    @PutMapping(
        value = "/artista/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleUpdateArtistaRequest(@PathVariable String id, @RequestBody Artista artista) {

        if (artistaRepository.existsById(id)) {
            artista._id = id;
            return new ResponseEntity<>(artistaRepository.save(artista), HttpStatus.OK);
        }

        return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
    }

    // ELIMINAR
    @DeleteMapping(
        value = "/artista/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> HandleDeleteArtistaRequest(@PathVariable String id) {

        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return new ResponseEntity<>("Artista eliminado", HttpStatus.OK);
        }

        return new ResponseEntity<>("Artista no encontrado", HttpStatus.NOT_FOUND);
    }
}