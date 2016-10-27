package fr.eni.expeditor.service;

import fr.eni.expeditor.entity.Role;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by Administrateur on 26/10/2016.
 */
@Stateless
public class GestionRoleBean extends AbstractService {

    /**
     *
     * @return
     */
    public List<Role> rechercherTous(){
        return (List<Role>) super.consulter(Role.class);
    }

    /**
     *
     * @param code
     * @return
     */
    public Role rechercher(String code){
        return getEntityManager().find(Role.class, code);
    }

}
