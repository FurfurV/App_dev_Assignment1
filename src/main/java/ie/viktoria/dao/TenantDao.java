package ie.viktoria.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDao implements ITenantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public void addTenant(){
//        String INSERT_QUERY ="INSERT INTO tenant(id, name, email, phoneNumber, eircode) VALUES (?,?,?,?,?)";
//    }

    public int countTenants() {
        return jdbcTemplate.queryForObject("Select COUNT(*) FROM tenant", Integer.class);
    }
}
