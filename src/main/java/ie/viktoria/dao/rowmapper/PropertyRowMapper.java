package ie.viktoria.dao.rowmapper;

import ie.viktoria.classes.Property;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PropertyRowMapper implements RowMapper<Property> {
    public Property mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Property property = new Property();
                property.setCapacity(rs.getInt("capacity"));
                property.setTenantsOccupy(rs.getInt("tenantsOccupy"));
                property.setCost(rs.getInt("cost"));
                property.setEircode(rs.getString("eircode"));
        return property;
    }
}
