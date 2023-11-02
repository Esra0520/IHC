/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.IHC.Controladores;

import com.example.IHC.Clases.Anuncios;
import com.example.IHC.Clases.Usuario;
import com.example.IHC.Interfaces.IAnunciosService;
import com.example.IHC.Interfaces.IUsuarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/usuario/")
@Controller
public class ControladorUsuario {
    String carpeta = "Usuario/";
    String carpeta2 = "Admin/";

    @Autowired
    private IUsuarioService service;
    
    @Autowired
    private IAnunciosService serviceA;
    

    @GetMapping("/nuevo") //localhost/cliente/nuevo
    public String NuevoUsuario() {
        return carpeta + "nuevoUsuario"; //nuevoCliente.html
    }
    
    @PostMapping("/registrar") //localhost/cliente/registrar
    public String RegistrarUsuario(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("correo") String correo,
            @RequestParam("user") String user,
            @RequestParam("cont") String contraseña,
            Model model) {
        //Aqui va el proceso de registrar
        Usuario c = new Usuario();
        c.setNombre(nom);
        c.setApellido(ape);
        c.setCorreo(correo);
        c.setUsername(user);
        c.setContra(contraseña);
        service.Guardar(c);
        return ListarUsuario(model);
    }

    @GetMapping("/")
    public String ListarUsuario(Model model) {
        model.addAttribute("usuarios", service.Listar());

        return carpeta + "listaUsuario";
    }
    
    @GetMapping("/listaAnuncio")
    public String ListarAnuncio(Model model) {
        List<Anuncios> anuncio = serviceA.Listar();

        model.addAttribute("anuncios", anuncio);

        return carpeta2 + "listaAnuncioU";
    }
    
    @PostMapping("/buscarAnuncio") //localhost/buscar
    public String BuscarAnuncio(@RequestParam("desc") String desc,
            Model model) {
        List<Anuncios> la = serviceA.Buscar(desc);

        model.addAttribute("anuncios", la);

        return carpeta2 + "listaAnuncio"; 
    }
}
