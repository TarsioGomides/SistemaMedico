package br.com.sistemamedico.controllers;

import br.com.sistemamedico.models.Paciente;
import br.com.sistemamedico.services.PacienteService;
import br.com.sistemamedico.validation.PacienteValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PacienteController {
    @Autowired
    PacienteService pacienteService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new PacienteValidation());
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Fornece um formulário para o cadastro de pacientes

     * RETORNO:   Retorna uma string com o nome da view que representa o formulário
     ***************************************************************************************************************/
    @RequestMapping("cadastroPaciente")
    public String formCadastroPaciente() {
        return "form-cadastro-paciente";
    }

    /***************************************************************************************************************
    * DESCRIÇÃO: Recebe um paciente como parâmetro e repassa para o serviço adequado realizar a lógica de negócio,
    de modo que um objeto do tipo ModelAndView adequado consiga ser montado.

    * RETORNO:   Retorna um objeto do tipo ModelAndView que contém o nome da view que deve ser exibida
    ***************************************************************************************************************/
    @RequestMapping("adicionaPaciente")
    public ModelAndView adiciona(@Valid Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(pacienteService.servicoAdicionarPaciente(paciente,result,redirectAttributes));

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Lista os pacientes existentes usando o serviço que ajuda na realização dessa tarefa

     * RETORNO:   Retorna um objeto do tipo ModelAndView que contém o nome da view que deve ser exibida e uma lista
     com os pacientes
     ***************************************************************************************************************/
//    @RequestMapping("listarPacientes")
//    public ModelAndView listar() {
//        ModelAndView modelAndView = new ModelAndView("listar-pacientes");
//        modelAndView.addObject("pacientes", pacienteService.servicoListarPaciente());
//
//        return modelAndView;
//    }

    @GetMapping(path = "listarPacientes")
    public ResponseEntity<?> listar() {
        return new ResponseEntity<>(pacienteService.servicoListarPaciente(), HttpStatus.OK);
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Mostra os dados de um paciente previamente cadastrado dentro de um formulário

     * RETORNO:   Retorna um objeto do tipo ModelAndView que contém o nome da view que deve ser exibida e um paciente
     ***************************************************************************************************************/
    @RequestMapping("mostraPaciente/{id}")
    public ModelAndView mostra(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(pacienteService.servicoMostrarPaciente(id, redirectAttributes, modelAndView));

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Chama um serviço que envia as alterações para o banco de dados

     * RETORNO:   Retorna uma string com o nome da view que deve ser exibida
     ***************************************************************************************************************/
    //@Post @PostMapping
    // @RequestMapping(value = "alterarPaciente", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @RequestMapping(value = "alterarPaciente")
    public String altera(@Valid @RequestBody Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes) {
        return pacienteService.servicoAlterarPaciente(paciente, result, redirectAttributes);
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Chama um serviço que executa a lógica de negócio para remoção do paciaente na base de dados

     * RETORNO:   Retorna uma string com o nome da view que deve ser exibida
     ***************************************************************************************************************/
    @RequestMapping("removerPaciente")
    public String remover(Integer id, RedirectAttributes redirectAttributes) {
        return pacienteService.servicoRemoverPaciente(id, redirectAttributes);
    }



    /********************************************************************************************************************/
    /********************************************************************************************************************/
    /********************************************************************************************************************/
    /********************************************************************************************************************/
    @RequestMapping("listarPacientesJSON")
    @ResponseBody//A resposta do spring vai ser o corpo da resposta
    public List<Paciente> listarJSON() {
        List<Paciente> listaDePacientes = pacienteService.servicoListarPaciente();

        return listaDePacientes;
    }

    @RequestMapping("removerPacienteJSON")
    @ResponseBody
    public String removerJSON(Integer id, RedirectAttributes redirectAttributes) {
        return pacienteService.servicoRemoverPaciente(id, redirectAttributes);
    }

    @RequestMapping("mostraPacienteJSON")
    public ModelAndView mostraJSON(Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(pacienteService.servicoMostrarPaciente(id, redirectAttributes, modelAndView));

        return modelAndView;
    }


}
