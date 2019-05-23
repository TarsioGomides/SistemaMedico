package br.com.sistemamedico.services;

import br.com.sistemamedico.daos.PacienteDao;
import br.com.sistemamedico.models.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Service
public class PacienteService {
    @Autowired
    private PacienteDao pacienteDao;

    /***************************************************************************************************************
     * DESCRIÇÃO: Verifica se tal paciente existe

     * RETORNO: Retorna um boolean que quando true significa que existe o paciente
     ***************************************************************************************************************/
    public boolean servicoVerificaExistenciaPaciente(Integer pacienteId) {
        if (pacienteId == null || pacienteDao.buscaPorId(pacienteId) == null)
            return false;
        else
            return true;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Verifica se há erros nos campos do paciente, caso não tenha, adiciona o paciente ao banco de dados

     * RETORNO: Retorna para o formulário de cadastro caso tenha erros no preenchimento dos dados, caso contrário,
     vai para a lista de pacientes
     ***************************************************************************************************************/
    public String servicoAdicionarPaciente(Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasFieldErrors("nome")) {
            redirectAttributes.addFlashAttribute("falhaNome", "O nome do paciente não pode ser vazio");
        }

        if (result.hasFieldErrors("idade")) {
            redirectAttributes.addFlashAttribute("falhaIdade", "A idade do paciente não pode ser vazia ou negativa");
        }

        if (result.hasFieldErrors("telefone")) {
            redirectAttributes.addFlashAttribute("falhaTelefone", "A telefone do paciente não pode ser vazio");
        }

        if(result.hasErrors()) {
            return "redirect:cadastroPaciente";//caso dê errado
        } else {
            pacienteDao.adicionaPaciente(paciente);
            redirectAttributes.addFlashAttribute("sucessoAdicionarPaciente", "Paciente cadastrado com sucesso");

            return "redirect:listarPacientes";//caso tudo esteja certo
        }
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Recupera a lista de pacientes cadastrados no banco de dados

     * RETORNO: Retorna a lista de pacientes cadastrados
     ***************************************************************************************************************/
    public List<Paciente> servicoListarPaciente() {
        return pacienteDao.listaPacientes();
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Antecede a operação de alteração dos dados do paciente, seu papel é buscar os dados de um paciente,
     caso o paciente exista

     * RETORNO: Retorna para a lista de pacientes caso o paciente buscado não exista, caso contrário, retorna
     o formulário preenchido com os dados que foram gravados previamente
     ***************************************************************************************************************/
    public String servicoMostrarPaciente(Integer id, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {

        if(id == null || pacienteDao.buscaPorId(id) == null) {
            redirectAttributes.addFlashAttribute("pacienteInexistenteMostrarPaciente", "O paciente buscado não existe");
            return "redirect:listarPacientes";
        }

        modelAndView.addObject("paciente", pacienteDao.buscaPorId(id));
        return "mostra-paciente";
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Realiza a alteração dos dados cadastrais do paciente caso todas as informações estejam preenchidas
     corretamente

     * RETORNO: Caso ocorra erro no preenchimento dos dados, retorna para o formulário para que os dados possam
     ser preenchidos de forma correta, caso contrário retorna para a lista de pacientes
     ***************************************************************************************************************/
    public String servicoAlterarPaciente(Paciente paciente, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasFieldErrors("nome")) {
            redirectAttributes.addFlashAttribute("falhaNome", "O nome do paciente não pode ser vazio");
        }

        if (result.hasFieldErrors("idade")) {
            redirectAttributes.addFlashAttribute("falhaIdade", "A idade do paciente não pode ser vazia ou negativa");
        }

        if (result.hasFieldErrors("telefone")) {
            redirectAttributes.addFlashAttribute("falhaTelefone", "A telefone do paciente não pode ser vazio");
        }

        if(result.hasErrors()) {
            return "redirect:cadastroPaciente";//caso dê errado
        } else {
            pacienteDao.alteraPaciente(paciente);
            redirectAttributes.addFlashAttribute("sucessoAlterarPaciente", "Paciente alterado com sucesso");

            return "redirect:listarPacientes";//caso tudo esteja certo
        }
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Remove um paciente do banco de dados

     * RETORNO: É levado para a página de listagem de pacientes, independentemente do usuário a ser removido
     existir ou não
     ***************************************************************************************************************/
    public String servicoRemoverPaciente(Integer id, RedirectAttributes redirectAttributes) {
        if(!servicoVerificaExistenciaPaciente(id)){
            redirectAttributes.addFlashAttribute("pacienteInexistenteRemoverPaciente", "O paciente que se tentou remover, não existe");
            return "redirect:listarPacientes";
        }

        pacienteDao.removePaciente(id);

        return "redirect:listarPacientes";
    }
}
