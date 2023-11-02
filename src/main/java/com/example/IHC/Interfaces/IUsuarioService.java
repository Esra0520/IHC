/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.IHC.Interfaces;

import com.example.IHC.Clases.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario c);
    public void Eliminar(int id);
    public List<Usuario> Buscar(String desc);
    public List<Usuario> BuscarxUyC(String username,String contra);


}
