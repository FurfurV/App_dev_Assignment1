package ie.viktoria.service;

import ie.viktoria.classes.Property;

import java.util.List;

public interface IPropertyService {
    boolean exists(String eircode);

    Property addNewProperty(int capacity, int tenantsOccupy, int cost, String eircode);

    List<Property> getAllProperties();

    List<Property> getAllPropertiesWithSpace();

    Property getPropertyByEircode(String eircode);

    int deleteProperty(String eircode);

    boolean checkPropertyHasSpace(String eircode);

    void updateOccupation(String eircode,int capacity);
}
