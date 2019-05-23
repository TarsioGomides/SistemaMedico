package br.com.sistemamedico.controllers;

import br.com.sistemamedico.models.Exame;
import br.com.sistemamedico.services.ExameService;
import br.com.sistemamedico.validation.ExameValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ExameController {
    @Autowired
    ExameService exameService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(new ExameValidation());
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Fornece um formulário para o cadastro de exames, pegando o pacienteId para identidificar a qual
     paciente o exame a ser cadastrado vai pertencer

     * RETORNO:   Retorna uma string com o nome da view que representa o formulário
     ***************************************************************************************************************/
    @RequestMapping("cadastroExame")
    public ModelAndView formCadastroExame(Integer pacienteId) {
        ModelAndView modelAndView = new ModelAndView("form-cadastro-exame");
        modelAndView.addObject("pacienteId", pacienteId);

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Recebe um exame como parâmetro e repassa para o serviço adequado realizar a lógica de negócio,
     de modo que um objeto do tipo ModelAndView adequado consiga ser montado.

     * RETORNO:   Retorna um objeto do tipo ModelAndView que contém o nome da view que deve ser exibida e o
     pacienteId que deve ser repassado
     ***************************************************************************************************************/
    @RequestMapping("adicionaExame")
    public ModelAndView adicionar(@Valid Exame exame, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(exameService.servicoAdicionarExame(exame, result, redirectAttributes));
        modelAndView.addObject("pacienteId", exame.getPaciente().getId());


        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Lista os exames de um paciente especifico

     * RETORNO:   Retorna um objeto do tipo ModelAndView que contém o nome da view que deve ser exibida e uma lista
     de exames
     ***************************************************************************************************************/
    @RequestMapping("listarExames")
    public ModelAndView listar(Integer pacienteId, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(exameService.servicoListarExames(pacienteId, modelAndView, redirectAttributes));

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO:

     * RETORNO:
     ***************************************************************************************************************/
    @RequestMapping("mostrarExame")
    public ModelAndView mostrar(Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(exameService.servicoMostrarExame(id, redirectAttributes, modelAndView));

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO:

     * RETORNO:
     * OBS: redirectAttributes não está sendo usado, foi criado para não ter conflito com o método listar
     ***************************************************************************************************************/
    @RequestMapping("alterarExame")
    public ModelAndView alterar(@Valid Exame exame, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(exameService.servicoAlterarExame(exame, result, redirectAttributes));
        modelAndView.addObject("pacienteId", exame.getPaciente().getId());//faz o binding com a próxima ação

        return modelAndView;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO:

     * RETORNO:
     * * OBS: redirectAttributes não está sendo usado, foi criado para não ter conflito com o método listar
     ***************************************************************************************************************/
    @RequestMapping("removerExame")
    public ModelAndView remover(Integer id, Integer pacienteId, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView(exameService.servicoRemoverExame(id, pacienteId, redirectAttributes));
        modelAndView.addObject("pacienteId", pacienteId);

        return modelAndView;
    }

}
