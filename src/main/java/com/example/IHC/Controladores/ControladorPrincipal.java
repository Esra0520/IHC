/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.IHC.Controladores;

import com.example.IHC.Clases.Anuncios;
import com.example.IHC.Clases.Usuario;
import com.example.IHC.Interfaces.IAnunciosService;
import com.example.IHC.Interfaces.IUsuarioService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/princ/")
@Controller
public class ControladorPrincipal {

    String carpeta = "Principal/";
    String carpeta2 = "Usuario/";

    @Autowired
    private IUsuarioService service;

    @Autowired
    private IAnunciosService serviceA;

    @WebServlet("/voiceSearch")
    public class VoiceSearchServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String voiceInput = request.getParameter("voiceInput");
            System.out.println("Entrada de voz: " + voiceInput); // Procesamiento de la entrada de voz
            // Aquí puedes realizar acciones adicionales con la entrada de voz, como buscar en una base de datos, etc.
        }
    }

    @GetMapping("/login")
    public String Login() {
        return carpeta + "login"; //nuevoCliente.html
    }

    @GetMapping("/nuevoUsuario") //localhost/cliente/nuevo
    public String NuevoUsuario() {
        return carpeta2 + "nuevoUsuario"; //nuevoCliente.html
    }

    @PostMapping("/registrarUsuario") //localhost/cliente/registrar
    public String RegistrarUsuario(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("correo") String correo,
            @RequestParam("user") String user,
            @RequestParam("contra") String contraseña,
            Model model) {
        //Aqui va el proceso de registrar
        Usuario c = new Usuario();
        c.setNombre(nom);
        c.setApellido(ape);
        c.setCorreo(correo);
        c.setUsername(user);
        c.setContra(contraseña);
        service.Guardar(c);
        return Login();
    }

    @GetMapping("/listaUsuario")
    public String ListarUsuario(Model model) {
        List<Usuario> usuario = service.Listar();

        model.addAttribute("usuarios", usuario);

        return carpeta + "listaUsuario";
    }

    @GetMapping("/eliminarUsuario")
    public String EliminarUsuario(@RequestParam("cod") int cod,
            Model model) {
        service.Eliminar(cod);
        return ListarUsuario(model);
    }

    @GetMapping("/editarUsuario") //localhost/editar
    public String EditarUsuario(@RequestParam("cod") int cod,
            Model model) {
        Optional<Usuario> usu = service.ConsultarId(cod);

        model.addAttribute("usuario", usu);
        return carpeta + "editarUsuario"; //editarCliente.html
    }

    @PostMapping("/actualizarUsuario") //localhost/actualizar
    public String ActualizarUsuario(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("correo") String correo,
            @RequestParam("username") String user,
            @RequestParam("contra") String contraseña,
            Model model) {

        //Aqui va el proceso de actualizar
        Usuario c = new Usuario();
        c.setId(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setCorreo(correo);
        c.setUsername(user);
        c.setContra(contraseña);

        //lista.add(c);
        service.Guardar(c); //cuando se manda ID -> Actualiza
        return ListarUsuario(model);
    }

    @PostMapping("/buscarUsuario") //localhost/buscar
    public String BuscarUsuario(@RequestParam("desc") String desc,
            Model model) {
        List<Usuario> lc = service.Buscar(desc);

        model.addAttribute("usuarios", lc);

        return carpeta + "listaUsuario"; //listaCliente.html
    }

    //---------------------------------------------------------------------//
    //---------------------------------------------------------------------//
    @GetMapping("/listaAnuncio")
    public String ListarAnuncio(Model model) {
        List<Anuncios> anuncio = serviceA.Listar();

        model.addAttribute("anuncios", anuncio);

        return carpeta + "listaAnuncio";
    }

    @GetMapping("/nuevoAnuncio") //localhost/cliente/nuevo
    public String NuevoAnuncio() {
        return carpeta + "nuevoAnuncio"; //nuevoCliente.html
    }

    @PostMapping("/registrarAnuncio") //localhost/cliente/registrar
    public String RegistrarAnuncio(
            @RequestParam("desc") String descripcion,
            Model model) {
        //Aqui va el proceso de registrar
        Anuncios c = new Anuncios();
        c.setDescripcion(descripcion);
        serviceA.Guardar(c);
        return ListarAnuncio(model);
    }

    @GetMapping("/eliminarAnuncio")
    public String EliminarAnuncio(@RequestParam("cod") int cod,
            Model model) {
        serviceA.Eliminar(cod);
        return ListarAnuncio(model);
    }

    @GetMapping("/editarAnuncio") //localhost/editar
    public String EditarAnuncio(@RequestParam("cod") int cod,
            Model model) {
        Optional<Anuncios> anu = serviceA.ConsultarId(cod);

        model.addAttribute("anuncio", anu);
        return carpeta + "editarAnuncio"; //editarCliente.html
    }

    @PostMapping("/actualizarAnuncio") //localhost/actualizar
    public String ActualizarAnuncio(@RequestParam("id") int cod,
            @RequestParam("descripcion") String desc,
            Model model) {

        //Aqui va el proceso de actualizar
        Anuncios c = new Anuncios();
        c.setId(cod);
        c.setDescripcion(desc);

        //lista.add(c);
        serviceA.Guardar(c); //cuando se manda ID -> Actualiza
        return ListarAnuncio(model);
    }

    @PostMapping("/buscarAnuncio") //localhost/buscar
    public String BuscarAnuncio(@RequestParam("desc") String desc,
            Model model) {
        List<Anuncios> la = serviceA.Buscar(desc);

        model.addAttribute("anuncios", la);

        return carpeta + "listaAnuncio";
    }

    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    //--------------------------------------------------------------------
    @GetMapping("/listaAnuncioU")
    public String ListarAnuncioU(Model model) {
        List<Anuncios> anuncio = serviceA.Listar();

        model.addAttribute("anuncios", anuncio);

        return carpeta2 + "listaAnuncioU";
    }

    @PostMapping("/buscarAnuncioU") //localhost/buscar
    public String BuscarAnuncioU(@RequestParam("desc") String desc,
            Model model) {
        List<Anuncios> la = serviceA.Buscar(desc);

        model.addAttribute("anuncios", la);

        return carpeta2 + "listaAnuncioU";
    }

    @PostMapping("/buscar")
    public String BuscarLogin(@RequestParam("user") String user,
            @RequestParam("contra") String contra,
            Model model, HttpSession session) {
        if (user.isEmpty() || contra.isEmpty()) {
            model.addAttribute("error", "Usuario y/o contraseña vacíos");
            return carpeta + "login";
        }

        if ("admin".equals(user) && "admin".equals(contra)) {
            return carpeta + "listaAnuncio";
        } else {
            List<Usuario> lu = service.BuscarxUyC(user, contra);
            if (lu.isEmpty()) {
                model.addAttribute("error", "Usuario y/o contraseña incorrectos");
                return carpeta + "login";
            } else {
                Usuario usuario = lu.get(0);
                session.setAttribute("usuario", usuario);
                model.addAttribute("usuarios", lu);
                return carpeta2 + "listaAnuncioU";
            }
        }
    }

    @GetMapping("/mostrarUsuario")
    public String mostrarUsuario(Model model, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            return carpeta2 + "mostrarUsuario"; // Suponiendo que tienes una vista llamada "mostrarUsuario"
        } else {
            // El usuario no ha iniciado sesión, redirigir a la página de inicio de sesión
            return carpeta + "login";
        }
    }

}
