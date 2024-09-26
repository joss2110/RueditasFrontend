package pe.edu.cibertec.RueditasFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rueditas")
public class ConsultaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {

        return "inicio";
    }
}
