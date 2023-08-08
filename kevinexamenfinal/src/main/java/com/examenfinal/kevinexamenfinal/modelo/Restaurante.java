package com.examenfinal.kevinexamenfinal.modelo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection ="restaurante")
@Data
public class Restaurante {

    @Id
    private Long id;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private List<Cliente> Listacliente;
    private Carta carta;


}
