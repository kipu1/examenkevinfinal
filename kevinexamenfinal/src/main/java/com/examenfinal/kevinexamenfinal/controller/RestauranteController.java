/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.examenfinal.kevinexamenfinal.controller;

import com.examenfinal.kevinexamenfinal.modelo.Restaurante;
import com.examenfinal.kevinexamenfinal.service.RestauranteServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author ASUS ROG
 */
@RestController
@RequestMapping("/api/restaurante")
public class RestauranteController {

    @Autowired
    RestauranteServiceImpl restauranteService;

    @GetMapping("/listar")
    public ResponseEntity<List<Restaurante>> listarProductos() {
        return new ResponseEntity<>(restauranteService.findByAll(),
                HttpStatus.OK);
    }

    @PostMapping("/crear")
    public ResponseEntity<Restaurante> crearProducto(
            @RequestBody Restaurante p) {
        return new ResponseEntity<>(restauranteService.save(p),
                HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Restaurante> actualizarProducto(@PathVariable Long id, @RequestBody Restaurante p) {
        Restaurante restaurante = restauranteService.findById(id);
        if (restaurante == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            try {
                restaurante.setNombre(p.getNombre());
                restaurante.setTelefono(p.getTelefono());
                restaurante.setCorreo(p.getCorreo());
                restaurante.setDireccion(p.getDireccion());
                restaurante.setListacliente(p.getListacliente());
                restaurante.setCarta(p.getCarta());

                return new ResponseEntity<>(restauranteService.save(p), HttpStatus.OK);
            } catch (DataAccessException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Restaurante> eliminarProducto(@PathVariable Long id) {
        restauranteService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
