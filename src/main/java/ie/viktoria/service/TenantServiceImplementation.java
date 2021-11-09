package ie.viktoria.service;

import ie.viktoria.dao.ITenantRepository;
import ie.viktoria.dao.TenantDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantServiceImplementation implements ITenantService{

    @Autowired
    ITenantRepository tenantRepository;

    @Override
    public int countTheTenants() {
        return tenantRepository.countTenants();
    }

}
