package pe.edu.cibertec.RueditasFrontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.RueditasFrontend.viewmodel.RequestConsultaModel;

@Controller
@RequestMapping("/rueditas")
public class ConsultaController {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        RequestConsultaModel requestConsultaModel = new RequestConsultaModel("00","",
                "","","","","");
        model.addAttribute("requestConsultaModel", requestConsultaModel);
        return "inicio";
    }
    @PostMapping("/consulta")
    public String autenticar(@RequestParam("placa") String placa, Model model) {

        String placaPattern = "^[A-Za-z]{3}-\\d{3}$";
        if (placa == null || placa.trim().length() == 0 || !placa.matches(placaPattern) ) {

            RequestConsultaModel requestConsultaModel = new RequestConsultaModel("01", "Debe ingresar una placa correcta", "","","","","");
            model.addAttribute("requestConsultaModel", requestConsultaModel);
            return "inicio";
        }


        RequestConsultaModel requestConsultaModel = new RequestConsultaModel("00", "", "KIA","Sportage","5","26000","Negro");
        model.addAttribute("requestConsultaModel", requestConsultaModel);
        return "consulta";
    }
}
