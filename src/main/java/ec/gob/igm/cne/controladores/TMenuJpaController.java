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
import ec.gob.igm.cne.entidades.TMenu;
import ec.gob.igm.cne.entidades.TPagina;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author desarrollo
 */
public class TMenuJpaController implements Serializable {

    public TMenuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TMenu TMenu) throws PreexistingEntityException, Exception {
        if (TMenu.getTMenuList() == null) {
            TMenu.setTMenuList(new ArrayList<TMenu>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMenu idMenuPadre = TMenu.getIdMenuPadre();
            if (idMenuPadre != null) {
                idMenuPadre = em.getReference(idMenuPadre.getClass(), idMenuPadre.getIdMenu());
                TMenu.setIdMenuPadre(idMenuPadre);
            }
            TPagina idPagina = TMenu.getIdPagina();
            if (idPagina != null) {
                idPagina = em.getReference(idPagina.getClass(), idPagina.getIdPagina());
                TMenu.setIdPagina(idPagina);
            }
            List<TMenu> attachedTMenuList = new ArrayList<TMenu>();
            for (TMenu TMenuListTMenuToAttach : TMenu.getTMenuList()) {
                TMenuListTMenuToAttach = em.getReference(TMenuListTMenuToAttach.getClass(), TMenuListTMenuToAttach.getIdMenu());
                attachedTMenuList.add(TMenuListTMenuToAttach);
            }
            TMenu.setTMenuList(attachedTMenuList);
            em.persist(TMenu);
            if (idMenuPadre != null) {
                idMenuPadre.getTMenuList().add(TMenu);
                idMenuPadre = em.merge(idMenuPadre);
            }
            if (idPagina != null) {
                idPagina.getTMenuList().add(TMenu);
                idPagina = em.merge(idPagina);
            }
            for (TMenu TMenuListTMenu : TMenu.getTMenuList()) {
                TMenu oldIdMenuPadreOfTMenuListTMenu = TMenuListTMenu.getIdMenuPadre();
                TMenuListTMenu.setIdMenuPadre(TMenu);
                TMenuListTMenu = em.merge(TMenuListTMenu);
                if (oldIdMenuPadreOfTMenuListTMenu != null) {
                    oldIdMenuPadreOfTMenuListTMenu.getTMenuList().remove(TMenuListTMenu);
                    oldIdMenuPadreOfTMenuListTMenu = em.merge(oldIdMenuPadreOfTMenuListTMenu);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTMenu(TMenu.getIdMenu()) != null) {
                throw new PreexistingEntityException("TMenu " + TMenu + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TMenu TMenu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMenu persistentTMenu = em.find(TMenu.class, TMenu.getIdMenu());
            TMenu idMenuPadreOld = persistentTMenu.getIdMenuPadre();
            TMenu idMenuPadreNew = TMenu.getIdMenuPadre();
            TPagina idPaginaOld = persistentTMenu.getIdPagina();
            TPagina idPaginaNew = TMenu.getIdPagina();
            List<TMenu> TMenuListOld = persistentTMenu.getTMenuList();
            List<TMenu> TMenuListNew = TMenu.getTMenuList();
            if (idMenuPadreNew != null) {
                idMenuPadreNew = em.getReference(idMenuPadreNew.getClass(), idMenuPadreNew.getIdMenu());
                TMenu.setIdMenuPadre(idMenuPadreNew);
            }
            if (idPaginaNew != null) {
                idPaginaNew = em.getReference(idPaginaNew.getClass(), idPaginaNew.getIdPagina());
                TMenu.setIdPagina(idPaginaNew);
            }
            List<TMenu> attachedTMenuListNew = new ArrayList<TMenu>();
            for (TMenu TMenuListNewTMenuToAttach : TMenuListNew) {
                TMenuListNewTMenuToAttach = em.getReference(TMenuListNewTMenuToAttach.getClass(), TMenuListNewTMenuToAttach.getIdMenu());
                attachedTMenuListNew.add(TMenuListNewTMenuToAttach);
            }
            TMenuListNew = attachedTMenuListNew;
            TMenu.setTMenuList(TMenuListNew);
            TMenu = em.merge(TMenu);
            if (idMenuPadreOld != null && !idMenuPadreOld.equals(idMenuPadreNew)) {
                idMenuPadreOld.getTMenuList().remove(TMenu);
                idMenuPadreOld = em.merge(idMenuPadreOld);
            }
            if (idMenuPadreNew != null && !idMenuPadreNew.equals(idMenuPadreOld)) {
                idMenuPadreNew.getTMenuList().add(TMenu);
                idMenuPadreNew = em.merge(idMenuPadreNew);
            }
            if (idPaginaOld != null && !idPaginaOld.equals(idPaginaNew)) {
                idPaginaOld.getTMenuList().remove(TMenu);
                idPaginaOld = em.merge(idPaginaOld);
            }
            if (idPaginaNew != null && !idPaginaNew.equals(idPaginaOld)) {
                idPaginaNew.getTMenuList().add(TMenu);
                idPaginaNew = em.merge(idPaginaNew);
            }
            for (TMenu TMenuListOldTMenu : TMenuListOld) {
                if (!TMenuListNew.contains(TMenuListOldTMenu)) {
                    TMenuListOldTMenu.setIdMenuPadre(null);
                    TMenuListOldTMenu = em.merge(TMenuListOldTMenu);
                }
            }
            for (TMenu TMenuListNewTMenu : TMenuListNew) {
                if (!TMenuListOld.contains(TMenuListNewTMenu)) {
                    TMenu oldIdMenuPadreOfTMenuListNewTMenu = TMenuListNewTMenu.getIdMenuPadre();
                    TMenuListNewTMenu.setIdMenuPadre(TMenu);
                    TMenuListNewTMenu = em.merge(TMenuListNewTMenu);
                    if (oldIdMenuPadreOfTMenuListNewTMenu != null && !oldIdMenuPadreOfTMenuListNewTMenu.equals(TMenu)) {
                        oldIdMenuPadreOfTMenuListNewTMenu.getTMenuList().remove(TMenuListNewTMenu);
                        oldIdMenuPadreOfTMenuListNewTMenu = em.merge(oldIdMenuPadreOfTMenuListNewTMenu);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TMenu.getIdMenu();
                if (findTMenu(id) == null) {
                    throw new NonexistentEntityException("The tMenu with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TMenu TMenu;
            try {
                TMenu = em.getReference(TMenu.class, id);
                TMenu.getIdMenu();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TMenu with id " + id + " no longer exists.", enfe);
            }
            TMenu idMenuPadre = TMenu.getIdMenuPadre();
            if (idMenuPadre != null) {
                idMenuPadre.getTMenuList().remove(TMenu);
                idMenuPadre = em.merge(idMenuPadre);
            }
            TPagina idPagina = TMenu.getIdPagina();
            if (idPagina != null) {
                idPagina.getTMenuList().remove(TMenu);
                idPagina = em.merge(idPagina);
            }
            List<TMenu> TMenuList = TMenu.getTMenuList();
            for (TMenu TMenuListTMenu : TMenuList) {
                TMenuListTMenu.setIdMenuPadre(null);
                TMenuListTMenu = em.merge(TMenuListTMenu);
            }
            em.remove(TMenu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TMenu> findTMenuEntities() {
        return findTMenuEntities(true, -1, -1);
    }

    public List<TMenu> findTMenuEntities(int maxResults, int firstResult) {
        return findTMenuEntities(false, maxResults, firstResult);
    }

    private List<TMenu> findTMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TMenu.class));
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

    public TMenu findTMenu(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TMenu.class, id);
        } finally {
            em.close();
        }
    }

    public int getTMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TMenu> rt = cq.from(TMenu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
