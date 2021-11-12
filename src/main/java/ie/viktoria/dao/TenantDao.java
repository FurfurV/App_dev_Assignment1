package ie.viktoria.dao;

import ie.viktoria.classes.Tenant;
import ie.viktoria.dao.rowmapper.TenantRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TenantDao implements ITenantRepository {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public int addTenant(String name,String email, String phoneNumber, String eircode){
        final String INSERT_SQL = "INSERT INTO tenant( name, email, phoneNumber, eircode)" +
                "VALUES (:name,:email,:phoneNumber,:eircode)";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name",name)
                .addValue("email",email)
                .addValue("phoneNumber",phoneNumber)
                .addValue("eircode",eircode);
        return namedParameterJdbcTemplate.update(INSERT_SQL,namedParameters);
    }

    public int removeTenant(int id){
        final String DELETE_SQL = "DELETE FROM tenant WHERE id=:id ";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id",id);
        return namedParameterJdbcTemplate.update(DELETE_SQL,namedParameters);
    }


    public Tenant findTenantByEircodeAndName(String name,String eircode){
        final String SQL = "SELECT * FROM tenant WHERE eircode =:eircode AND name=:name";
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("name",name)
                .addValue("eircode", eircode);
        return namedParameterJdbcTemplate.queryForObject(SQL,
                namedParameters, new TenantRowMapper());
    }

    public List<Tenant> findListOfTenants(String eircode){
        final String SQL = "SELECT * FROM tenant WHERE eircode =:eircode";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("eircode", eircode);
        return namedParameterJdbcTemplate.query(SQL,
                namedParameters, new TenantRowMapper());
    }

}
