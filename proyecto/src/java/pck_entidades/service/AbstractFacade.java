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
import pck_entidades.Proyecto;
import pck_entidades.Sprint;
import pck_entidades.Tarea;
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
        String consulta = "select u.idusuario ||','|| u.nombre ||','|| u.apellido ||','|| r.nombrerol ||','|| u.idrol ||','|| u.idequipo"
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
                json.put("idequipo", partes[5]);
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
    
    public String listarTareaScrum(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select distinct t.idtarea ||','|| "
        + "t.nombre from tarea t join usuario u on t.idusuario = u.idusuario join equipo e"
        + " on u.idequipo = e.idequipo where e.idequipo="+id+";");
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
    public String EditarTareaUs(Tarea tarea) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("update tarea set "
        + "estado = \'"+ tarea.getEstado()+"\' where idtarea = "+tarea.getIdtarea()+";");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idtarea) "
        + "from tarea where idtarea = " 
            + tarea.getIdtarea() + " AND estado = \'" + tarea.getEstado() + "\';");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
       
    }
    
    public String eliminarTarea(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("delete from tarea where idtarea = "+id+" ;");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idtarea) "
        + "from tarea where idtarea = "+id+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado ==0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }
    
    public String EditarSprint(Sprint sprint) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("update sprint set "
        + "nombre = \'"+ sprint.getNombre()+ " \',fechaini = \'"+sprint.getFechaini()+"\', fechafin = \'"
        +sprint.getFechafin()+"\', estado = \'"+sprint.getEstado()+"\' where idsprint = "+sprint.getIdsprint()+";");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idsprint)"
        + "from sprint where nombre = '" + sprint.getNombre()+ "' AND fechaini = '" + sprint.getFechaini()+ "' AND fechafin = '" +
        sprint.getFechafin()+"' AND estado = '"+sprint.getEstado()+"' AND idsprint = "+sprint.getIdsprint()+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", true);
        }
        return json.toString();
       
    }
    
    public String listaSprint(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select s.idsprint ||','|| s.nombre "
        + "from sprint s join userhistories us on"
                + " s.idus = us.idus join proyecto p on p.idproyecto = us.idproyecto"
                + " join equipo e on e.idproyecto = p.idproyecto  "
                + " where e.idequipo="+id+";");
        List resultado = q.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < resultado.size(); i++) {
            JSONObject json = new JSONObject();
            String[] partes = resultado.get(i).toString().split(",");
            json.put("idsprint", partes[0]);
            json.put("nombre", partes[1]);
            jsonArray.put(json);
        }
        return jsonArray.toString();
    }
    
    public String agregarSprint(Sprint sprint) throws JSONException {
        javax.persistence.Query q1 = getEntityManager().createNativeQuery("insert into sprint "
        + "(idsprint, nombre, fechaini, fechafin, estado, idus) values ("+sprint.getIdsprint()+", "
        + "\'"+sprint.getNombre()+"\', \'"+sprint.getFechaini()+"\', \'"+sprint.getFechafin()+"\',\'"+sprint.getEstado()+"\' ,"
        + ""+sprint.getUserhistories().getIdus()+");");
        q1.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idsprint) "
        + "from sprint where idsprint = "+sprint.getIdsprint()+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }
    public String listarUsuario(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select idusuario || ',' || "
        + "nombre || ',' || apellido from usuario where idrol <> 1 and idequipo = "+id+" ;");
        List resultado = q.getResultList();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < resultado.size(); i++) {
            JSONObject json = new JSONObject();
            String[] partes = resultado.get(i).toString().split(",");
            json.put("idusuario", partes[0]);
            json.put("nombre", partes[1]);
            jsonArray.put(json);
        }
        return jsonArray.toString();
    }
    
    public String agregarUsuario(Usuario user) throws JSONException {
        javax.persistence.Query q1 = getEntityManager().createNativeQuery("insert into usuario "
        + "(idusuario, nombre, apellido, correo, idequipo, idrol, contrasenha, cedula) "
        + "values ("+user.getIdusuario()+", \'"+user.getNombre()+"\', \'"+user.getApellido()+"\', "
        + "\'"+user.getCorreo()+"\',"+user.getEquipo().getIdequipo()+","+user.getRol().getIdrol()+", "
        + "\'"+user.getContrasenha()+"\', \'"+user.getCedula()+"\'); ");
        q1.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idusuario) "
        + "from usuario where idusuario = "+user.getIdusuario()+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }
    
    public String editarUsuarioxSc(Usuario user) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("update usuario set "
        + "nombre = \'"+ user.getNombre()+"\', apellido = \'"+user.getApellido()+"\', "
        + "correo = \'"+user.getCorreo()+ "\',idrol = "+user.getRol().getIdrol()+" where idusuario = "+user.getIdusuario()+";");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idusuario) "
        + "from usuario where idusuario = "+user.getIdusuario()+" and nombre = \'"+ user.getNombre()+"\' "
        + "and apellido = \'"+user.getApellido()+"\' and correo = \'"+user.getCorreo()+"\'and idrol = "+user.getRol().getIdrol()+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", true);
        }
        return json.toString();
    }
    
    public String editarUsuario(Usuario user) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("update usuario set "
        + "nombre = \'"+ user.getNombre()+"\', apellido = \'"+user.getApellido()+"\', "
        + "correo = \'"+user.getCorreo()+"\', contrasenha = \'"+ user.getContrasenha()+"\' where idusuario = "+user.getIdusuario()+";");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idusuario) "
        + "from usuario where idusuario = "+user.getIdusuario()+" and nombre = \'"+ user.getNombre()+"\' "
        + "and apellido = \'"+user.getApellido()+"\' and correo = \'"+user.getCorreo()+"\' "
        + "and contrasenha = \'"+ user.getContrasenha()+"\';");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }
    
 public String eliminarUs(int id) throws JSONException {
        javax.persistence.Query q = getEntityManager().createNativeQuery("delete from usuario where idusuario = "+id+" ;");
        q.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idusuario) "
        + "from usuario where idusuario = "+id+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado ==0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }
 public String agregarProyecto(Proyecto proyecto) throws JSONException {
        javax.persistence.Query q1 = getEntityManager().createNativeQuery("insert into proyecto "
        + "(idproyecto, nombre, descripcion, fechaini, fechafin, estado) "
        + "values ("+proyecto.getIdproyecto()+", \'"+proyecto.getNombre()+"\', \'"+proyecto.getDescripcion()+"\', "
        + "\'"+proyecto.getFechaini()+"\',\'"+proyecto.getFechafin()+"\', \'"+proyecto.getEstado()+"\'); ");
        q1.executeUpdate();
        javax.persistence.Query q2 = getEntityManager().createNativeQuery("select count(idproyecto) "
        + "from proyecto where idproyecto = "+proyecto.getIdproyecto()+";");
        long resultado = (Long)q2.getSingleResult();
        JSONObject json = new JSONObject();
        if(resultado>0){
            json.put("respuesta", true);
        }else{
            json.put("respuesta", false);
        }
        return json.toString();
    }

}
