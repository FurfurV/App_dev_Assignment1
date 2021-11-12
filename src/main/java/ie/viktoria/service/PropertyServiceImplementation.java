package ie.viktoria.service;

import ie.viktoria.classes.Property;
import ie.viktoria.dao.IPropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PropertyServiceImplementation implements IPropertyService{

    @Autowired
    IPropertyRepository propertyRepository;

    public static final String ERROR_MESS="Oh no, something went wrong";

    public boolean exists(String eircode){
        return propertyRepository.exists(eircode);
    }

    public Property addNewProperty(int capacity,int tenantsOccupy,int cost, String eircode){
        if(propertyRepository.exists(eircode)){
            return null;
        }
        propertyRepository.addProperty(capacity,tenantsOccupy,cost,eircode);

        return propertyRepository.getPropertyByEircode(eircode);
    }

    public List<Property> getAllProperties() {
        if(propertyRepository.getAllProperties() == null){
            log.error(ERROR_MESS);
        }
        return propertyRepository.getAllProperties();
    }

    public List<Property> getAllPropertiesWithSpace() {
        if(propertyRepository.getAllPropertiesWithSpace() == null){
            log.error(ERROR_MESS);
        }
        return propertyRepository.getAllPropertiesWithSpace();
    }

    public boolean checkPropertyHasSpace(String eircode) {
        return propertyRepository.checkPropertyHasSpace(eircode);
    }

    public Property getPropertyByEircode(String eircode) {
        return propertyRepository.getPropertyByEircode(eircode);
    }

    public int deleteProperty(String eircode) {
        return propertyRepository.deleteProperty(eircode);
    }

    public void updateOccupation(String eircode,int capacity) {
        propertyRepository.updateOccupation(eircode,capacity);
    }
}
