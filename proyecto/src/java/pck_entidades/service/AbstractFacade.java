/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pck_entidades.service;

import java.util.List;
import javax.persistence.EntityManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pck_entidades.Usuario;

/**
 *
 * @author user
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
    public String login(Usuario user) throws JSONException {
        String consulta = "select u.idusuario ||','|| u.nombre ||','|| u.apellido ||','|| r.nombrerol ||','|| u.idrol"
                + " from usuario u join rol r on u.idrol = r.idrol where u.cedula = \'" 
            + user.getCedula() + "\' AND u.contrasenha = \'" + user.getContrasenha() + "\';";
        
           javax.persistence.Query q = getEntityManager().createNativeQuery(consulta);
           List usuarioLog = q.getResultList();
           JSONObject json = new JSONObject();
           if (usuarioLog.size()>0){
                String respuesta = (String)q.getSingleResult();
                String[] partes = respuesta.split(",");
                json.put("idusuario", partes[0]);
                json.put("nombre", partes[1]);
                json.put("apellido", partes[2]);
                json.put("nombrerol", partes[3]);
                json.put("idrol", partes[4]);
                return json.toString();
           }
           else
            {
                return "false";
            }
    }
    public String listarTareaUs(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select distinct idtarea ||','|| "
        + "nombre from tarea  where idusuario="+id+";");
        List resultado = q.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < resultado.size(); i++) {
            JSONObject json = new JSONObject();
            String[] partes = resultado.get(i).toString().split(",");
            json.put("idtarea", partes[0]);
            json.put("nombre", partes[1]);
            jsonArray.put(json);
        }
        return jsonArray.toString();
    }
}
