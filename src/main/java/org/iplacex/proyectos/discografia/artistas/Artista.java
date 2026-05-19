package org.iplacex.proyectos.discografia.artistas;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("artistas")
public class Artista {

    @Id
    public String _id;

    public String nombre;

    public List<String> estilos;

    public int anioFundacion; //para no poner la otra palabra porque con ñ NO PASABA JAJAJ

    public boolean estaActivo;
}