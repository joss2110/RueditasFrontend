package pe.edu.cibertec.RueditasFrontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.RueditasFrontend.viewmodel.RequestConsultaModel;
import pe.edu.cibertec.RueditasFrontend.viewmodel.ResponseConsultaModel;

@Controller
@RequestMapping("/rueditas")
public class ConsultaController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        ResponseConsultaModel responseConsultaModel = new ResponseConsultaModel("00","",
                "","","","","");
        model.addAttribute("responseConsultaModel", responseConsultaModel);
        return "inicio";
    }
    @PostMapping("/consulta")
    public String autenticar(@RequestParam("placa") String placa, Model model) {

        String placaPattern = "^[A-Za-z]{3}-\\d{3}$";
        if (placa == null || placa.trim().length() == 0 || !placa.matches(placaPattern) ) {

            ResponseConsultaModel responseConsultaModel = new ResponseConsultaModel("01", "Debe ingresar una placa correcta", "","","","","");
            model.addAttribute("responseConsultaModel", responseConsultaModel);
            return "inicio";
        }

        try {

            String endpoint = "http://localhost:8081/consulta/vehiculo";
            RequestConsultaModel requestConsultaModel = new RequestConsultaModel(placa);
            ResponseConsultaModel responseConsultaModel = restTemplate.postForObject(endpoint, requestConsultaModel, ResponseConsultaModel.class);

            if (responseConsultaModel.codigo().equals("00")){

                model.addAttribute("responseConsultaModel", responseConsultaModel);
                return "consulta";

            } else {
                model.addAttribute("responseConsultaModel", responseConsultaModel);
                return "inicio";
            }

        } catch(Exception e) {

            ResponseConsultaModel responseConsultaModel = new ResponseConsultaModel("02", "Ocurrió un error en la consula, intentelo de nuevo mas tarde", "","","","","");
            model.addAttribute("responseConsultaModel", responseConsultaModel);
            System.out.println(e.getMessage());
            return "inicio";

        }

    }
}
