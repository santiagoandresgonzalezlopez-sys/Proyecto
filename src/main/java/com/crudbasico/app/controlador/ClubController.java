package com.crudbasico.app.controlador;
import java.util.List;
import com.crudbasico.app.model.Club;
import com.crudbasico.app.model.Entrenador;
import com.crudbasico.app.model.Jugador;
import com.crudbasico.app.model.Asociacion;
import com.crudbasico.app.model.Competicion;
import com.crudbasico.app.repository.ClubRepository;
import com.crudbasico.app.repository.EntrenadorRepository;
import com.crudbasico.app.repository.JugadorRepository;
import com.crudbasico.app.repository.AsociacionRepository;
import com.crudbasico.app.repository.CompeticionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;
    
    @Autowired
    private EntrenadorRepository entrenadorRepository;
    
    @Autowired
    private AsociacionRepository asociacionRepository;
    
    @Autowired
    private JugadorRepository jugadorRepository;  // ← AGREGAR
    
    @Autowired
    private CompeticionRepository competicionRepository;  // ← AGREGAR

    // Página 1: Formulario para crear club
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("jugadores", jugadorRepository.findAll());  // ← AGREGAR
        model.addAttribute("competiciones", competicionRepository.findAll());  // ← AGREGAR
        model.addAttribute("titulo", "Nuevo Club");
        model.addAttribute("contenido", "club/index");
        return "layout";
    }

    // Guardar club (con jugadores y competiciones)
    @PostMapping("/guardar")
    public String guardarClub(@ModelAttribute Club club,
                              @RequestParam(value = "jugadores", required = false) List<Long> jugadoresIds,
                              @RequestParam(value = "competiciones", required = false) List<Long> competicionesIds) {
        
        // Asignar jugadores seleccionados
        if (jugadoresIds != null && !jugadoresIds.isEmpty()) {
            List<Jugador> jugadoresSeleccionados = jugadorRepository.findAllById(jugadoresIds);
            club.setJugadores(jugadoresSeleccionados);
        }
        
        // Asignar competiciones seleccionadas
        if (competicionesIds != null && !competicionesIds.isEmpty()) {
            List<Competicion> competicionesSeleccionadas = competicionRepository.findAllById(competicionesIds);
            club.setCompeticiones(competicionesSeleccionadas);
        }
        
        clubRepository.save(club);
        return "redirect:/club/listar";
    }

    // Página 2: Listar todos los clubes
    @GetMapping("/listar")
    public String listarClubes(Model model) {
        model.addAttribute("clubes", clubRepository.findAll());
        model.addAttribute("titulo", "Lista de Clubes");
        model.addAttribute("contenido", "club/listar");
        return "layout";
    }

    // Editar club
    @GetMapping("/editar/{id}")
    public String editarClub(@PathVariable Long id, Model model) {
        Club club = clubRepository.findById(id).orElse(null);
        model.addAttribute("club", club);
        model.addAttribute("entrenadores", entrenadorRepository.findAll());
        model.addAttribute("asociaciones", asociacionRepository.findAll());
        model.addAttribute("jugadores", jugadorRepository.findAll());  // ← AGREGAR
        model.addAttribute("competiciones", competicionRepository.findAll());  // ← AGREGAR
        model.addAttribute("titulo", "Editar Club");
        model.addAttribute("contenido", "club/index");
        return "layout";
    }

    // Eliminar club
    @GetMapping("/eliminar/{id}")
    public String eliminarClub(@PathVariable Long id) {
        clubRepository.deleteById(id);
        return "redirect:/club/listar";
    }
}