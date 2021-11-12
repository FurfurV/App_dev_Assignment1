package ie.viktoria.dao;

import ie.viktoria.classes.Property;
import ie.viktoria.dao.rowmapper.PropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropertyDao implements IPropertyRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public boolean exists(String eircode) {
        final String SQL = "SELECT COUNT(*) FROM property WHERE property.eircode =:eircode ";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("eircode", eircode);
        return 1 == namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, Integer.class);
    }


    public int updateOccupation(String eircode, int value) {
        final String SQL = "UPDATE property SET tenantsOccupy = tenantsOccupy +:value"
                + " WHERE property.eircode = :eircode";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("value",value)
                .addValue("eircode", eircode);
        int numChanged = namedParameterJdbcTemplate.update(SQL, namedParameters);
        return numChanged;
    }


    public Property getPropertyByEircode(String eircode) {
        try {
            String SQL = "SELECT * FROM property WHERE eircode =:eircode";
            SqlParameterSource namedParameters = new MapSqlParameterSource()
                    .addValue("eircode", eircode);
            return namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, new PropertyRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public int addProperty(int capacity, int tenantsOccupy, int cost, String eircode) {
        final String INSERT_SQL = "INSERT INTO property( capacity, tenantsOccupy, cost, eircode)" +
                "VALUES (:capacity,:tenantsOccupy,:cost,:eircode)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("capacity", capacity)
                .addValue("tenantsOccupy", tenantsOccupy)
                .addValue("cost", cost)
                .addValue("eircode", eircode);
        return namedParameterJdbcTemplate.update(INSERT_SQL, namedParameters);
    }

    public int deleteProperty(String eircode){
        final String DELETE_SQL = "DELETE FROM property WHERE eircode=:eircode";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("eircode", eircode);
        return namedParameterJdbcTemplate.update(DELETE_SQL, namedParameters);
    }

    public List<Property> getAllProperties() {
        try {
            return namedParameterJdbcTemplate.query("SELECT * FROM property", new PropertyRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List<Property> getAllPropertiesWithSpace() {
        try {
            return namedParameterJdbcTemplate.query(
                    "SELECT * FROM property WHERE capacity > tenantsOccupy", new PropertyRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkPropertyHasSpace(String eircode){
        final String SQL = "SELECT COUNT(*) FROM property WHERE (capacity > tenantsOccupy) AND eircode =:eircode";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("eircode", eircode);
        return 1 == namedParameterJdbcTemplate.queryForObject(SQL, namedParameters, Integer.class);
    }
}
