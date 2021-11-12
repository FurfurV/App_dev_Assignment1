package ie.viktoria.service;

import ie.viktoria.classes.Tenant;
import ie.viktoria.dao.IPropertyRepository;
import ie.viktoria.dao.ITenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TenantServiceImplementation implements ITenantService{

    @Autowired
    ITenantRepository tenantRepository;

    @Autowired
    IPropertyRepository propertyRepository;

    @Override
    public Tenant addNewTenant(String name,String email, String phone, String eircode){
        tenantRepository.addTenant(name,email,phone,eircode);

        return tenantRepository.findTenantByEircodeAndName(name,eircode);
    }

    public int removeTenant(int id){
        return tenantRepository.removeTenant(id);
    }

    public List<Tenant> getTenantList(String eircode) {
        return tenantRepository.findListOfTenants(eircode);
    }
}
