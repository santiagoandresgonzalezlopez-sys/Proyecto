package com.crudbasico.app.controlador;

import com.crudbasico.app.model.Jugador;
import com.crudbasico.app.repository.JugadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/jugador")
public class JugadorController {

    @Autowired
    private JugadorRepository jugadorRepository;

    // Página 1: Formulario para crear jugador
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("titulo", "Nuevo Jugador");
        model.addAttribute("contenido", "jugador/index");
        return "layout";
    }

    // Guardar jugador
    @PostMapping("/guardar")
    public String guardarJugador(@ModelAttribute Jugador jugador) {
        jugadorRepository.save(jugador);
        return "redirect:/jugador/listar";
    }

    // Página 2: Listar todos los jugadores
    @GetMapping("/listar")
    public String listarJugadores(Model model) {
        model.addAttribute("jugadores", jugadorRepository.findAll());
        model.addAttribute("titulo", "Lista de Jugadores");
        model.addAttribute("contenido", "jugador/listar");
        return "layout";
    }

    // Eliminar jugador
    @PostMapping("/eliminar/{id}")
    public String eliminarJugador(@PathVariable Long id) {
        jugadorRepository.deleteById(id);
        return "redirect:/jugador/listar";
    }

    // Editar jugador
    @GetMapping("/editar/{id}")
    public String editarJugador(@PathVariable Long id, Model model) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Jugador no encontrado: " + id));
        model.addAttribute("jugador", jugador);
        model.addAttribute("titulo", "Editar Jugador");
        model.addAttribute("contenido", "jugador/index");
        return "layout";
    }
}