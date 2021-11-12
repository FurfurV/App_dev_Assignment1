package ie.viktoria.classes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private int capacity;
    private int tenantsOccupy;
    private int cost;
    private String eircode;
}
