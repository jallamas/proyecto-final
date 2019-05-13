/**
 * 
 */
package com.salesianostriana.dam.gestionhermandad.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.dam.gestionhermandad.model.HermanoProvisional;
import com.salesianostriana.dam.gestionhermandad.services.HermanoProvisionalServicio;

/**
 * @author José Antonio Llamas Álvarez
 *
 */
@Controller
public class HermanoProvisionalController {

	private HermanoProvisionalServicio hermanoProvisionalServicio;

	public HermanoProvisionalController(HermanoProvisionalServicio hermanoprovisionalservicio) {
		this.hermanoProvisionalServicio = hermanoprovisionalservicio;
	}

	@GetMapping("/registro")
	public String mostrarRegistroForm(Model model) {
		model.addAttribute("hermanoProvisional", new HermanoProvisional());
		return "registro_form";
	}

	@PostMapping("/registro/submit")
	public String procesarAltaProvisional(@ModelAttribute("hermanoProvisional") HermanoProvisional hermanoProvisional) {
		hermanoProvisional.setFechaAlta(LocalDate.now());
		hermanoProvisionalServicio.save(hermanoProvisional);
		return "user/vistaHermanoProvisional";
	}

	@GetMapping({ "/listarTodosProv" })
	public String listarTodos(Model model) {
		model.addAttribute("listaProv", hermanoProvisionalServicio.findAll());
		return "admin/listaHermanosProv";
	}

	@GetMapping("/validar/{id}")
	public String validar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.validarHermanoProvisional(hermanoProvisionalServicio.findById(id));
		hermanoProvisionalServicio.deleteById(id);
		return "redirect:/listarTodosProv";
	}

	@GetMapping("/borrar/{id}")
	public String borrar(@PathVariable("id") long id) {
		hermanoProvisionalServicio.deleteById(id);
		return "redirect:/listarTodosProv";
	}

}
