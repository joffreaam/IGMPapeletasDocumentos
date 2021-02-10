/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TDignidadXUsuario;
import ec.gob.igm.cne.entidades.TUsuario;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TUsuarioJpaController implements Serializable {

    public TUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TUsuario TUsuario) throws PreexistingEntityException, Exception {
        if (TUsuario.getTDignidadXUsuarioList() == null) {
            TUsuario.setTDignidadXUsuarioList(new ArrayList<TDignidadXUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TDignidadXUsuario> attachedTDignidadXUsuarioList = new ArrayList<TDignidadXUsuario>();
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuarioToAttach : TUsuario.getTDignidadXUsuarioList()) {
                TDignidadXUsuarioListTDignidadXUsuarioToAttach = em.getReference(TDignidadXUsuarioListTDignidadXUsuarioToAttach.getClass(), TDignidadXUsuarioListTDignidadXUsuarioToAttach.getId());
                attachedTDignidadXUsuarioList.add(TDignidadXUsuarioListTDignidadXUsuarioToAttach);
            }
            TUsuario.setTDignidadXUsuarioList(attachedTDignidadXUsuarioList);
            em.persist(TUsuario);
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuario : TUsuario.getTDignidadXUsuarioList()) {
                TUsuario oldIdUsuarioOfTDignidadXUsuarioListTDignidadXUsuario = TDignidadXUsuarioListTDignidadXUsuario.getIdUsuario();
                TDignidadXUsuarioListTDignidadXUsuario.setIdUsuario(TUsuario);
                TDignidadXUsuarioListTDignidadXUsuario = em.merge(TDignidadXUsuarioListTDignidadXUsuario);
                if (oldIdUsuarioOfTDignidadXUsuarioListTDignidadXUsuario != null) {
                    oldIdUsuarioOfTDignidadXUsuarioListTDignidadXUsuario.getTDignidadXUsuarioList().remove(TDignidadXUsuarioListTDignidadXUsuario);
                    oldIdUsuarioOfTDignidadXUsuarioListTDignidadXUsuario = em.merge(oldIdUsuarioOfTDignidadXUsuarioListTDignidadXUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTUsuario(TUsuario.getIdUsuario()) != null) {
                throw new PreexistingEntityException("El usuario: " + TUsuario + " ya existe.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TUsuario TUsuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUsuario persistentTUsuario = em.find(TUsuario.class, TUsuario.getIdUsuario());
            List<TDignidadXUsuario> TDignidadXUsuarioListOld = persistentTUsuario.getTDignidadXUsuarioList();
            List<TDignidadXUsuario> TDignidadXUsuarioListNew = TUsuario.getTDignidadXUsuarioList();
            List<TDignidadXUsuario> attachedTDignidadXUsuarioListNew = new ArrayList<TDignidadXUsuario>();
            if(TDignidadXUsuarioListNew != null){
                for (TDignidadXUsuario TDignidadXUsuarioListNewTDignidadXUsuarioToAttach : TDignidadXUsuarioListNew) {
                    TDignidadXUsuarioListNewTDignidadXUsuarioToAttach = em.getReference(TDignidadXUsuarioListNewTDignidadXUsuarioToAttach.getClass(), TDignidadXUsuarioListNewTDignidadXUsuarioToAttach.getId());
                    attachedTDignidadXUsuarioListNew.add(TDignidadXUsuarioListNewTDignidadXUsuarioToAttach);
                }
            }
            TDignidadXUsuarioListNew = attachedTDignidadXUsuarioListNew;
            TUsuario.setTDignidadXUsuarioList(TDignidadXUsuarioListNew);
            TUsuario = em.merge(TUsuario);
            for (TDignidadXUsuario TDignidadXUsuarioListOldTDignidadXUsuario : TDignidadXUsuarioListOld) {
                if (!TDignidadXUsuarioListNew.contains(TDignidadXUsuarioListOldTDignidadXUsuario)) {
                    TDignidadXUsuarioListOldTDignidadXUsuario.setIdUsuario(null);
                    TDignidadXUsuarioListOldTDignidadXUsuario = em.merge(TDignidadXUsuarioListOldTDignidadXUsuario);
                }
            }
            for (TDignidadXUsuario TDignidadXUsuarioListNewTDignidadXUsuario : TDignidadXUsuarioListNew) {
                if (!TDignidadXUsuarioListOld.contains(TDignidadXUsuarioListNewTDignidadXUsuario)) {
                    TUsuario oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario = TDignidadXUsuarioListNewTDignidadXUsuario.getIdUsuario();
                    TDignidadXUsuarioListNewTDignidadXUsuario.setIdUsuario(TUsuario);
                    TDignidadXUsuarioListNewTDignidadXUsuario = em.merge(TDignidadXUsuarioListNewTDignidadXUsuario);
                    if (oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario != null && !oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario.equals(TUsuario)) {
                        oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario.getTDignidadXUsuarioList().remove(TDignidadXUsuarioListNewTDignidadXUsuario);
                        oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario = em.merge(oldIdUsuarioOfTDignidadXUsuarioListNewTDignidadXUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = TUsuario.getIdUsuario();
                if (findTUsuario(id) == null) {
                    throw new NonexistentEntityException("El usuario con el ID: " + id + " no existe.");
                }
                System.out.println("Exception--->"+ex.getMessage());
            }            
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TUsuario TUsuario;
            try {
                TUsuario = em.getReference(TUsuario.class, id);
                TUsuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("El usuario con el ID " + id + " no existe.", enfe);
            }
            List<TDignidadXUsuario> TDignidadXUsuarioList = TUsuario.getTDignidadXUsuarioList();
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuario : TDignidadXUsuarioList) {
                TDignidadXUsuarioListTDignidadXUsuario.setIdUsuario(null);
                TDignidadXUsuarioListTDignidadXUsuario = em.merge(TDignidadXUsuarioListTDignidadXUsuario);
            }
            em.remove(TUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TUsuario> findTUsuarioEntities() {
        return findTUsuarioEntities(true, -1, -1);
    }

    public List<TUsuario> findTUsuarioEntities(int maxResults, int firstResult) {
        return findTUsuarioEntities(false, maxResults, firstResult);
    }

    private List<TUsuario> findTUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TUsuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public TUsuario findTUsuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TUsuario> rt = cq.from(TUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
