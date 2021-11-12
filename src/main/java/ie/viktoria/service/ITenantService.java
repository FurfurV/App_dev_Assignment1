package ie.viktoria.service;

import ie.viktoria.classes.Tenant;

import java.util.List;

public interface ITenantService {

    Tenant addNewTenant(String name, String email, String phoneNumber, String eircode);

    int removeTenant(int id);

    List<Tenant> getTenantList(String eircode);
}
