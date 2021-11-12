package ie.viktoria.dao;

import ie.viktoria.classes.Property;

import java.util.List;

public interface IPropertyRepository {

    boolean exists(String eircode);

    int updateOccupation(String eircode, int capacity);

    Property getPropertyByEircode(String eircode);

    int addProperty(int capacity, int tenantsOccupy, int cost, String eircode);

    List<Property> getAllProperties();

    List<Property> getAllPropertiesWithSpace();

    int deleteProperty(String eircode);

    boolean checkPropertyHasSpace(String eircode);
}
