/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.IHC.Repositorio;

import com.example.IHC.Clases.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends JpaRepository<Usuario,Integer>{
    @Query(value="SELECT * FROM usuario "
            + "WHERE username LIKE %?1% "
            + "OR contra LIKE %?1% ",nativeQuery=true)
    List<Usuario> findForAll(String username,String contra);
    
    
    @Query(value="SELECT * FROM usuario "
            + "WHERE nombre LIKE %?1% "
            + "OR apellido LIKE %?1% "
            + "OR correo LIKE %?1% "
            + "OR username LIKE %?1% "
            + "OR contra LIKE %?1% ",nativeQuery=true)
    List<Usuario> findForAll(String desc);
}
