/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.IHC.Repositorio;

import com.example.IHC.Clases.Anuncios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnuncios extends JpaRepository<Anuncios,Integer>{
    @Query(value="SELECT * FROM anuncio "
            + "WHERE descripcion LIKE %?1% ",nativeQuery=true)
    List<Anuncios> findForAll(String desc);
}
