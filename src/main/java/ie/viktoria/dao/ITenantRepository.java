package ie.viktoria.dao;

import ie.viktoria.classes.Tenant;

import java.util.List;

public interface ITenantRepository {

    int addTenant(String name, String email, String phoneNumber, String eircode);

    Tenant findTenantByEircodeAndName(String name,String eircode);

    int removeTenant(int id);

    List<Tenant> findListOfTenants(String eircode);
}
