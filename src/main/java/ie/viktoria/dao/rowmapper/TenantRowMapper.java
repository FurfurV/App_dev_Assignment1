package ie.viktoria.dao.rowmapper;

import ie.viktoria.classes.Tenant;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class TenantRowMapper implements RowMapper<Tenant> {
    public Tenant mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Tenant tenant = new Tenant(rs.getInt("id"), rs.getString("name"),
                rs.getString("email"),rs.getString("phoneNumber"), rs.getString("eircode"));
        return tenant;
    }
}
