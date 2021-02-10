/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.gob.igm.cne.controladores;

import ec.gob.igm.cne.controladores.exceptions.NonexistentEntityException;
import ec.gob.igm.cne.controladores.exceptions.PreexistingEntityException;
import ec.gob.igm.cne.entidades.TDignidad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ec.gob.igm.cne.entidades.TProduccionDisenio;
import java.util.ArrayList;
import java.util.List;
import ec.gob.igm.cne.entidades.TUsuarios;
import ec.gob.igm.cne.entidades.TDignidadXUsuario;
import ec.gob.igm.cne.entidades.TProduccionPapeletas;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author desarrollo
 */
public class TDignidadJpaController implements Serializable {

    public TDignidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TDignidad TDignidad) throws PreexistingEntityException, Exception {
        if (TDignidad.getTProduccionDisenioList() == null) {
            TDignidad.setTProduccionDisenioList(new ArrayList<TProduccionDisenio>());
        }
        if (TDignidad.getTUsuariosList() == null) {
            TDignidad.setTUsuariosList(new ArrayList<TUsuarios>());
        }
        if (TDignidad.getTDignidadXUsuarioList() == null) {
            TDignidad.setTDignidadXUsuarioList(new ArrayList<TDignidadXUsuario>());
        }
        if (TDignidad.getTProduccionPapeletasList() == null) {
            TDignidad.setTProduccionPapeletasList(new ArrayList<TProduccionPapeletas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<TProduccionDisenio> attachedTProduccionDisenioList = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenioToAttach : TDignidad.getTProduccionDisenioList()) {
                TProduccionDisenioListTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListTProduccionDisenioToAttach.getClass(), TProduccionDisenioListTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioList.add(TProduccionDisenioListTProduccionDisenioToAttach);
            }
            TDignidad.setTProduccionDisenioList(attachedTProduccionDisenioList);
            List<TUsuarios> attachedTUsuariosList = new ArrayList<TUsuarios>();
            for (TUsuarios TUsuariosListTUsuariosToAttach : TDignidad.getTUsuariosList()) {
                TUsuariosListTUsuariosToAttach = em.getReference(TUsuariosListTUsuariosToAttach.getClass(), TUsuariosListTUsuariosToAttach.getIdUsuario());
                attachedTUsuariosList.add(TUsuariosListTUsuariosToAttach);
            }
            TDignidad.setTUsuariosList(attachedTUsuariosList);
            List<TDignidadXUsuario> attachedTDignidadXUsuarioList = new ArrayList<TDignidadXUsuario>();
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuarioToAttach : TDignidad.getTDignidadXUsuarioList()) {
                TDignidadXUsuarioListTDignidadXUsuarioToAttach = em.getReference(TDignidadXUsuarioListTDignidadXUsuarioToAttach.getClass(), TDignidadXUsuarioListTDignidadXUsuarioToAttach.getId());
                attachedTDignidadXUsuarioList.add(TDignidadXUsuarioListTDignidadXUsuarioToAttach);
            }
            TDignidad.setTDignidadXUsuarioList(attachedTDignidadXUsuarioList);
            List<TProduccionPapeletas> attachedTProduccionPapeletasList = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletasToAttach : TDignidad.getTProduccionPapeletasList()) {
                TProduccionPapeletasListTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasList.add(TProduccionPapeletasListTProduccionPapeletasToAttach);
            }
            TDignidad.setTProduccionPapeletasList(attachedTProduccionPapeletasList);
            em.persist(TDignidad);
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TDignidad.getTProduccionDisenioList()) {
                TDignidad oldCodDignidadOfTProduccionDisenioListTProduccionDisenio = TProduccionDisenioListTProduccionDisenio.getCodDignidad();
                TProduccionDisenioListTProduccionDisenio.setCodDignidad(TDignidad);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
                if (oldCodDignidadOfTProduccionDisenioListTProduccionDisenio != null) {
                    oldCodDignidadOfTProduccionDisenioListTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListTProduccionDisenio);
                    oldCodDignidadOfTProduccionDisenioListTProduccionDisenio = em.merge(oldCodDignidadOfTProduccionDisenioListTProduccionDisenio);
                }
            }
            for (TUsuarios TUsuariosListTUsuarios : TDignidad.getTUsuariosList()) {
                TDignidad oldIdDignidadOfTUsuariosListTUsuarios = TUsuariosListTUsuarios.getIdDignidad();
                TUsuariosListTUsuarios.setIdDignidad(TDignidad);
                TUsuariosListTUsuarios = em.merge(TUsuariosListTUsuarios);
                if (oldIdDignidadOfTUsuariosListTUsuarios != null) {
                    oldIdDignidadOfTUsuariosListTUsuarios.getTUsuariosList().remove(TUsuariosListTUsuarios);
                    oldIdDignidadOfTUsuariosListTUsuarios = em.merge(oldIdDignidadOfTUsuariosListTUsuarios);
                }
            }
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuario : TDignidad.getTDignidadXUsuarioList()) {
                TDignidad oldIdDignidadOfTDignidadXUsuarioListTDignidadXUsuario = TDignidadXUsuarioListTDignidadXUsuario.getIdDignidad();
                TDignidadXUsuarioListTDignidadXUsuario.setIdDignidad(TDignidad);
                TDignidadXUsuarioListTDignidadXUsuario = em.merge(TDignidadXUsuarioListTDignidadXUsuario);
                if (oldIdDignidadOfTDignidadXUsuarioListTDignidadXUsuario != null) {
                    oldIdDignidadOfTDignidadXUsuarioListTDignidadXUsuario.getTDignidadXUsuarioList().remove(TDignidadXUsuarioListTDignidadXUsuario);
                    oldIdDignidadOfTDignidadXUsuarioListTDignidadXUsuario = em.merge(oldIdDignidadOfTDignidadXUsuarioListTDignidadXUsuario);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TDignidad.getTProduccionPapeletasList()) {
                TDignidad oldCodDignidadOfTProduccionPapeletasListTProduccionPapeletas = TProduccionPapeletasListTProduccionPapeletas.getCodDignidad();
                TProduccionPapeletasListTProduccionPapeletas.setCodDignidad(TDignidad);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
                if (oldCodDignidadOfTProduccionPapeletasListTProduccionPapeletas != null) {
                    oldCodDignidadOfTProduccionPapeletasListTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListTProduccionPapeletas);
                    oldCodDignidadOfTProduccionPapeletasListTProduccionPapeletas = em.merge(oldCodDignidadOfTProduccionPapeletasListTProduccionPapeletas);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTDignidad(TDignidad.getCodDignidad()) != null) {
                throw new PreexistingEntityException("TDignidad " + TDignidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TDignidad TDignidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidad persistentTDignidad = em.find(TDignidad.class, TDignidad.getCodDignidad());
            List<TProduccionDisenio> TProduccionDisenioListOld = persistentTDignidad.getTProduccionDisenioList();
            List<TProduccionDisenio> TProduccionDisenioListNew = TDignidad.getTProduccionDisenioList();
            List<TUsuarios> TUsuariosListOld = persistentTDignidad.getTUsuariosList();
            List<TUsuarios> TUsuariosListNew = TDignidad.getTUsuariosList();
            List<TDignidadXUsuario> TDignidadXUsuarioListOld = persistentTDignidad.getTDignidadXUsuarioList();
            List<TDignidadXUsuario> TDignidadXUsuarioListNew = TDignidad.getTDignidadXUsuarioList();
            List<TProduccionPapeletas> TProduccionPapeletasListOld = persistentTDignidad.getTProduccionPapeletasList();
            List<TProduccionPapeletas> TProduccionPapeletasListNew = TDignidad.getTProduccionPapeletasList();
            List<TProduccionDisenio> attachedTProduccionDisenioListNew = new ArrayList<TProduccionDisenio>();
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenioToAttach : TProduccionDisenioListNew) {
                TProduccionDisenioListNewTProduccionDisenioToAttach = em.getReference(TProduccionDisenioListNewTProduccionDisenioToAttach.getClass(), TProduccionDisenioListNewTProduccionDisenioToAttach.getIdRegistro());
                attachedTProduccionDisenioListNew.add(TProduccionDisenioListNewTProduccionDisenioToAttach);
            }
            TProduccionDisenioListNew = attachedTProduccionDisenioListNew;
            TDignidad.setTProduccionDisenioList(TProduccionDisenioListNew);
            List<TUsuarios> attachedTUsuariosListNew = new ArrayList<TUsuarios>();
            for (TUsuarios TUsuariosListNewTUsuariosToAttach : TUsuariosListNew) {
                TUsuariosListNewTUsuariosToAttach = em.getReference(TUsuariosListNewTUsuariosToAttach.getClass(), TUsuariosListNewTUsuariosToAttach.getIdUsuario());
                attachedTUsuariosListNew.add(TUsuariosListNewTUsuariosToAttach);
            }
            TUsuariosListNew = attachedTUsuariosListNew;
            TDignidad.setTUsuariosList(TUsuariosListNew);
            List<TDignidadXUsuario> attachedTDignidadXUsuarioListNew = new ArrayList<TDignidadXUsuario>();
            for (TDignidadXUsuario TDignidadXUsuarioListNewTDignidadXUsuarioToAttach : TDignidadXUsuarioListNew) {
                TDignidadXUsuarioListNewTDignidadXUsuarioToAttach = em.getReference(TDignidadXUsuarioListNewTDignidadXUsuarioToAttach.getClass(), TDignidadXUsuarioListNewTDignidadXUsuarioToAttach.getId());
                attachedTDignidadXUsuarioListNew.add(TDignidadXUsuarioListNewTDignidadXUsuarioToAttach);
            }
            TDignidadXUsuarioListNew = attachedTDignidadXUsuarioListNew;
            TDignidad.setTDignidadXUsuarioList(TDignidadXUsuarioListNew);
            List<TProduccionPapeletas> attachedTProduccionPapeletasListNew = new ArrayList<TProduccionPapeletas>();
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletasToAttach : TProduccionPapeletasListNew) {
                TProduccionPapeletasListNewTProduccionPapeletasToAttach = em.getReference(TProduccionPapeletasListNewTProduccionPapeletasToAttach.getClass(), TProduccionPapeletasListNewTProduccionPapeletasToAttach.getIdRegistro());
                attachedTProduccionPapeletasListNew.add(TProduccionPapeletasListNewTProduccionPapeletasToAttach);
            }
            TProduccionPapeletasListNew = attachedTProduccionPapeletasListNew;
            TDignidad.setTProduccionPapeletasList(TProduccionPapeletasListNew);
            TDignidad = em.merge(TDignidad);
            for (TProduccionDisenio TProduccionDisenioListOldTProduccionDisenio : TProduccionDisenioListOld) {
                if (!TProduccionDisenioListNew.contains(TProduccionDisenioListOldTProduccionDisenio)) {
                    TProduccionDisenioListOldTProduccionDisenio.setCodDignidad(null);
                    TProduccionDisenioListOldTProduccionDisenio = em.merge(TProduccionDisenioListOldTProduccionDisenio);
                }
            }
            for (TProduccionDisenio TProduccionDisenioListNewTProduccionDisenio : TProduccionDisenioListNew) {
                if (!TProduccionDisenioListOld.contains(TProduccionDisenioListNewTProduccionDisenio)) {
                    TDignidad oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio = TProduccionDisenioListNewTProduccionDisenio.getCodDignidad();
                    TProduccionDisenioListNewTProduccionDisenio.setCodDignidad(TDignidad);
                    TProduccionDisenioListNewTProduccionDisenio = em.merge(TProduccionDisenioListNewTProduccionDisenio);
                    if (oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio != null && !oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio.equals(TDignidad)) {
                        oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio.getTProduccionDisenioList().remove(TProduccionDisenioListNewTProduccionDisenio);
                        oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio = em.merge(oldCodDignidadOfTProduccionDisenioListNewTProduccionDisenio);
                    }
                }
            }
            for (TUsuarios TUsuariosListOldTUsuarios : TUsuariosListOld) {
                if (!TUsuariosListNew.contains(TUsuariosListOldTUsuarios)) {
                    TUsuariosListOldTUsuarios.setIdDignidad(null);
                    TUsuariosListOldTUsuarios = em.merge(TUsuariosListOldTUsuarios);
                }
            }
            for (TUsuarios TUsuariosListNewTUsuarios : TUsuariosListNew) {
                if (!TUsuariosListOld.contains(TUsuariosListNewTUsuarios)) {
                    TDignidad oldIdDignidadOfTUsuariosListNewTUsuarios = TUsuariosListNewTUsuarios.getIdDignidad();
                    TUsuariosListNewTUsuarios.setIdDignidad(TDignidad);
                    TUsuariosListNewTUsuarios = em.merge(TUsuariosListNewTUsuarios);
                    if (oldIdDignidadOfTUsuariosListNewTUsuarios != null && !oldIdDignidadOfTUsuariosListNewTUsuarios.equals(TDignidad)) {
                        oldIdDignidadOfTUsuariosListNewTUsuarios.getTUsuariosList().remove(TUsuariosListNewTUsuarios);
                        oldIdDignidadOfTUsuariosListNewTUsuarios = em.merge(oldIdDignidadOfTUsuariosListNewTUsuarios);
                    }
                }
            }
            for (TDignidadXUsuario TDignidadXUsuarioListOldTDignidadXUsuario : TDignidadXUsuarioListOld) {
                if (!TDignidadXUsuarioListNew.contains(TDignidadXUsuarioListOldTDignidadXUsuario)) {
                    TDignidadXUsuarioListOldTDignidadXUsuario.setIdDignidad(null);
                    TDignidadXUsuarioListOldTDignidadXUsuario = em.merge(TDignidadXUsuarioListOldTDignidadXUsuario);
                }
            }
            for (TDignidadXUsuario TDignidadXUsuarioListNewTDignidadXUsuario : TDignidadXUsuarioListNew) {
                if (!TDignidadXUsuarioListOld.contains(TDignidadXUsuarioListNewTDignidadXUsuario)) {
                    TDignidad oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario = TDignidadXUsuarioListNewTDignidadXUsuario.getIdDignidad();
                    TDignidadXUsuarioListNewTDignidadXUsuario.setIdDignidad(TDignidad);
                    TDignidadXUsuarioListNewTDignidadXUsuario = em.merge(TDignidadXUsuarioListNewTDignidadXUsuario);
                    if (oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario != null && !oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario.equals(TDignidad)) {
                        oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario.getTDignidadXUsuarioList().remove(TDignidadXUsuarioListNewTDignidadXUsuario);
                        oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario = em.merge(oldIdDignidadOfTDignidadXUsuarioListNewTDignidadXUsuario);
                    }
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListOldTProduccionPapeletas : TProduccionPapeletasListOld) {
                if (!TProduccionPapeletasListNew.contains(TProduccionPapeletasListOldTProduccionPapeletas)) {
                    TProduccionPapeletasListOldTProduccionPapeletas.setCodDignidad(null);
                    TProduccionPapeletasListOldTProduccionPapeletas = em.merge(TProduccionPapeletasListOldTProduccionPapeletas);
                }
            }
            for (TProduccionPapeletas TProduccionPapeletasListNewTProduccionPapeletas : TProduccionPapeletasListNew) {
                if (!TProduccionPapeletasListOld.contains(TProduccionPapeletasListNewTProduccionPapeletas)) {
                    TDignidad oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas = TProduccionPapeletasListNewTProduccionPapeletas.getCodDignidad();
                    TProduccionPapeletasListNewTProduccionPapeletas.setCodDignidad(TDignidad);
                    TProduccionPapeletasListNewTProduccionPapeletas = em.merge(TProduccionPapeletasListNewTProduccionPapeletas);
                    if (oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas != null && !oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas.equals(TDignidad)) {
                        oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas.getTProduccionPapeletasList().remove(TProduccionPapeletasListNewTProduccionPapeletas);
                        oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas = em.merge(oldCodDignidadOfTProduccionPapeletasListNewTProduccionPapeletas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = TDignidad.getCodDignidad();
                if (findTDignidad(id) == null) {
                    throw new NonexistentEntityException("The tDignidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TDignidad TDignidad;
            try {
                TDignidad = em.getReference(TDignidad.class, id);
                TDignidad.getCodDignidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TDignidad with id " + id + " no longer exists.", enfe);
            }
            List<TProduccionDisenio> TProduccionDisenioList = TDignidad.getTProduccionDisenioList();
            for (TProduccionDisenio TProduccionDisenioListTProduccionDisenio : TProduccionDisenioList) {
                TProduccionDisenioListTProduccionDisenio.setCodDignidad(null);
                TProduccionDisenioListTProduccionDisenio = em.merge(TProduccionDisenioListTProduccionDisenio);
            }
            List<TUsuarios> TUsuariosList = TDignidad.getTUsuariosList();
            for (TUsuarios TUsuariosListTUsuarios : TUsuariosList) {
                TUsuariosListTUsuarios.setIdDignidad(null);
                TUsuariosListTUsuarios = em.merge(TUsuariosListTUsuarios);
            }
            List<TDignidadXUsuario> TDignidadXUsuarioList = TDignidad.getTDignidadXUsuarioList();
            for (TDignidadXUsuario TDignidadXUsuarioListTDignidadXUsuario : TDignidadXUsuarioList) {
                TDignidadXUsuarioListTDignidadXUsuario.setIdDignidad(null);
                TDignidadXUsuarioListTDignidadXUsuario = em.merge(TDignidadXUsuarioListTDignidadXUsuario);
            }
            List<TProduccionPapeletas> TProduccionPapeletasList = TDignidad.getTProduccionPapeletasList();
            for (TProduccionPapeletas TProduccionPapeletasListTProduccionPapeletas : TProduccionPapeletasList) {
                TProduccionPapeletasListTProduccionPapeletas.setCodDignidad(null);
                TProduccionPapeletasListTProduccionPapeletas = em.merge(TProduccionPapeletasListTProduccionPapeletas);
            }
            em.remove(TDignidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TDignidad> findTDignidadEntities() {
        return findTDignidadEntities(true, -1, -1);
    }

    public List<TDignidad> findTDignidadEntities(int maxResults, int firstResult) {
        return findTDignidadEntities(false, maxResults, firstResult);
    }

    private List<TDignidad> findTDignidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TDignidad.class));
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

    public TDignidad findTDignidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TDignidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTDignidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TDignidad> rt = cq.from(TDignidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
