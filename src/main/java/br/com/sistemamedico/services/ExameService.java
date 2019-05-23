package br.com.sistemamedico.services;

import br.com.sistemamedico.daos.ExameDao;
import br.com.sistemamedico.models.Exame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ExameService {
    @Autowired
    private ExameDao exameDao;
    @Autowired
    private PacienteService pacienteService;

    /***************************************************************************************************************
     * DESCRIÇÃO: Verifica se tal paciente possui exames cadastrados no banco

     * RETORNO: Retorna um boolean que quando true significa que existe exames cadastrados para o paciente
     ***************************************************************************************************************/
    public boolean servicoVerificaExistenciaExames(Integer pacienteId) {
        if (exameDao.listarExames(pacienteId).isEmpty()) {
            return false;
        }
        return true;
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Verifica se há erros nos campos do exame, caso não tenha, adiciona o exame ao banco de dados

     * RETORNO: Retorna para o formulário de cadastro caso tenha erros no preenchimento dos dados, caso contrário,
     vai para a lista de exames do paciente específico
     ***************************************************************************************************************/
    public String servicoAdicionarExame(Exame exame, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasFieldErrors("nomeExame")) {
            redirectAttributes.addFlashAttribute("falhaNomeExame", "O nome do exame não pode ser vazio");
        }

        if (result.hasFieldErrors("descricao")) {
            redirectAttributes.addFlashAttribute("falhaDescricao", "A descrição do exame não pode ser vazia");
        }

        if (result.hasFieldErrors("dataRealizacao")) {
            redirectAttributes.addFlashAttribute("falhaDataRealizacao", "A data deve seguir o formato dd/mm/aaaa");
        }

        if(result.hasErrors()) {
            return "redirect:cadastroExame";//caso dê errado
        } else {
            exameDao.adicionaExame(exame);
            redirectAttributes.addFlashAttribute("sucessoAdicionarExame", "Exame cadastrado com sucesso");

            return "redirect:listarExames";//caso tudo esteja certo
        }
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Caso o paciente não exista, não temos como ter uma lista de exames associadas ao mesmo.
     Caso contrário a lista de exames do paciente é buscada

     * RETORNO: Volta-se para a lista de pacientes em caso de problemas, mas quando tudo está correto, retorna a
     view que mostra os exames do paciente específico
     ***************************************************************************************************************/
    public String servicoListarExames(Integer pacienteId, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        //entra caso o paciente não tenha exames cadastrados
        if (!servicoVerificaExistenciaExames(pacienteId))
            modelAndView.addObject("pacienteSemExames", "O paciente não possue exames cadastrados");

        if (!pacienteService.servicoVerificaExistenciaPaciente(pacienteId)) {//entra no if se não existir um paciente com tal id
            redirectAttributes.addFlashAttribute("pacienteInexistenteListarExames", "O paciente que se tentou listar os exames, não existe");
            return "redirect:listarPacientes";
        } else {
            modelAndView.addObject("pacienteId", pacienteId);
            modelAndView.addObject("exames", exameDao.listarExames(pacienteId));
            return "listar-exames";
        }
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Antecede a operação de alteração dos dados do exame, seu papel é buscar os dados de um exame
     específico para um paciente específico, caso o exame exista

     * RETORNO: Retorna para a lista de exames do paciente caso o exame buscado não exista, caso contrário, retorna
     o formulário preenchido com os dados que foram gravados previamente
     ***************************************************************************************************************/
    public String servicoMostrarExame(Integer id, RedirectAttributes redirectAttributes, ModelAndView modelAndView) {
        if(id == null || exameDao.buscaPorId(id) == null) {
            redirectAttributes.addFlashAttribute("exameInexistenteMostrarExame", "O exame buscado não existe");
            return "redirect:listarExames";
        }

        modelAndView.addObject("exame", exameDao.buscaPorId(id));
        return "mostra-exame";
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Realiza a alteração dos dados cadastrais do exame caso todas as informações estejam preenchidas
     corretamente

     * RETORNO: Caso ocorra erro no preenchimento dos dados, retorna para o formulário para que os dados possam
     ser preenchidos de forma correta, caso contrário retorna para a lista de exames do paciente
     ***************************************************************************************************************/
    public String servicoAlterarExame(Exame exame, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasFieldErrors("nomeExame")) {
            redirectAttributes.addFlashAttribute("falhaNomeExame", "O nome do exame não pode ser vazio");
        }

        if (result.hasFieldErrors("descricao")) {
            redirectAttributes.addFlashAttribute("falhaDescricao", "A descrição do exame não pode ser vazia");
        }

        if (result.hasFieldErrors("dataRealizacao")) {
            redirectAttributes.addFlashAttribute("falhaDataRealizacao", "A data deve seguir o formato dd/mm/aaaa");
        }

        if(result.hasErrors()) {
            return "redirect:cadastroExame";//caso dê errado
        } else {
            exameDao.alteraExame(exame);
            redirectAttributes.addFlashAttribute("sucessoAlterarExame", "Exame alterado com sucesso");

            return "redirect:listarExames";//caso dê certo
        }
    }

    /***************************************************************************************************************
     * DESCRIÇÃO: Remove um exame do banco de dados

     * RETORNO: Caso o paciente e o exame associado ao mesmo existam, o usuário é levado de volta para a página
     de listagem de exames, caso contrário, ele é direcionado para a listagem de pacientes se o paciente
     não existir ou para a listagem de exames se o exame não existir
     ***************************************************************************************************************/
    public String servicoRemoverExame(Integer id, Integer pacienteId, RedirectAttributes redirectAttributes) {
        if(!pacienteService.servicoVerificaExistenciaPaciente(pacienteId)) {
            redirectAttributes.addFlashAttribute("pacienteInexistenteRemoverExame", "O paciente do qual se tentou remover um exame, não existe");
            return "redirect:listarPacientes";
        } else if(id == null || exameDao.buscaPorId(id) == null) {
            redirectAttributes.addFlashAttribute("exameInexistenteRemoverExame", "Você tentou remover um exame inexistente para esse paciente");
            return "redirect:listarExames";
        } else {
            exameDao.removeExame(id);
            return "redirect:listarExames";
        }
    }

}


