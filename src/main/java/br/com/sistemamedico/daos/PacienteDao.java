package br.com.sistemamedico.daos;

import br.com.sistemamedico.models.Paciente;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class PacienteDao {

    @PersistenceContext
    private EntityManager manager;

    /*INSERE UM PACIENTE NO BANCO DE DADOS*/
    public void adicionaPaciente(Paciente paciente) {
        manager.persist(paciente);
    }

    /*ATUALIZA OS DADOS DO PACIENTE NO BANCO DE DADOS*/
    public void alteraPaciente(Paciente paciente) {
        manager.merge(paciente);
    }

    /*RETORNA A LISTA DE PACIENTES NO BANCO DE DADOS*/
    public List<Paciente> listaPacientes() {
        return manager.createQuery("select p from Paciente p").getResultList();
    }

    /*SELECIONA UM PACIENTE DO BANCO DE DADOS*/
    public Paciente buscaPorId(Integer id) {
        return manager.find(Paciente.class ,id);
    }

    /*REMOVE UM PACIENTE DO BANCO DE DADOS*/
    public void removePaciente(Integer id) {
        Paciente pacienteARemover = buscaPorId(id);
        manager.remove(pacienteARemover);
    }

}
