package br.com.sistemamedico.daos;

import br.com.sistemamedico.models.Exame;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ExameDao {

    @PersistenceContext
    private EntityManager manager;

    /*INSERE UM EXAME NO BANCO DE DADOS*/
    public void adicionaExame(Exame exame) {
        manager.persist(exame);
    }

    /*ATUALIZA OS DADOS DO EXAME NO BANCO DE DADOS*/
    public void alteraExame(Exame exame) {
        manager.merge(exame);
    }

    /*RETORNA A LISTA DE EXAMES PARA UM  PACIENTE ESPECIFICO NO BANCO DE DADOS*/
    public List<Exame> listarExames(Integer pacienteId) {
        return manager.createQuery("select e from Exame e where e.paciente.id = :pacienteId")
        .setParameter("pacienteId", pacienteId).getResultList();
    }

    /*SELECIONA UM EXAME DO BANCO DE DADOS*/
    public Exame buscaPorId(Integer id) {
        return manager.find(Exame.class ,id);
    }

    /*REMOVE UMA EXAME DO BANCO DE DADOS*/
    public void removeExame(Integer id) {
        Exame exameARemover = buscaPorId(id);
        manager.remove(exameARemover);
    }
}
