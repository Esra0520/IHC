/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.IHC.Servicios;

import com.example.IHC.Clases.Anuncios;
import com.example.IHC.Interfaces.IAnunciosService;
import com.example.IHC.Repositorio.IAnuncios;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnunciosService implements IAnunciosService {

    @Autowired
    private IAnuncios data;

    @Override
    public List<Anuncios> Listar() {
        return (List<Anuncios>) data.findAll();
    }

    @Override
    public Optional<Anuncios> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Anuncios c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Anuncios> Buscar(String desc) {
        return data.findForAll(desc);
    }

}
