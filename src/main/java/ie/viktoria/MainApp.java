package ie.viktoria;

import ie.viktoria.classes.Property;
import ie.viktoria.classes.Tenant;
import ie.viktoria.config.ConfigH2;
import ie.viktoria.service.IPropertyService;
import ie.viktoria.service.ITenantService;
import ie.viktoria.service.PropertyServiceImplementation;
import ie.viktoria.service.TenantServiceImplementation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigH2.class);
        ITenantService tenantService = context.getBean(TenantServiceImplementation.class);
        IPropertyService propertyService = context.getBean(PropertyServiceImplementation.class);


//        System.out.println("tenants added" , tenantService.addTheTenant());
//        System.out.println("There are " + tenantService.countTheTenants() + " tenants in the database.");
        boolean dontExit = true;

        while (dontExit){
            System.out.printf("%50s"," \uD83C\uDFE0 Welcome! \uD83C\uDFE1 \n");
            System.out.printf("%-10s %s", "Type 1", "Search for a house by Eircode, listing the details of the household including all tenants\n");
            System.out.printf("%-10s %s", "Type 2", "View a list of houses\n");
            System.out.printf("%-10s %s", "Type 3", "View a list of houses with space in them\n");
            System.out.printf("%-10s %s", "Type 4", "Add a new house (ensuring unique Eircode)\n");
            System.out.printf("%-10s %s", "Type 5", "Add a new tenant and assign that tenant to a household (subject to capacity)\n");
            System.out.printf("%-10s %s", "Type 6", "Move a person from one household to another (subject to capacity)\n");
            System.out.printf("%-10s %s", "Type 7", "Delete a household, along with its occupants\n");
            System.out.printf("%-10s %s", "Type 8", "Delete a tenant, and remove them from the house in which they reside (reduce the number of occupants)\n");
            System.out.printf("%-10s %s", "Type 9", "Display some statistics\n");
            System.out.printf("%-10s %s", "Type 10", "Quit\n");
            System.out.print("What would you like to do? >> ");

            Scanner input = new Scanner(System.in);
            int choice = Integer.parseInt(input.nextLine());

            switch(choice) {
                case 1:
                    System.out.println("\n--------List of details of household and tenants--------");
                    System.out.print("What is the Eircode of the house? >> ");

                    String eircode = input.nextLine();

                    printProperty(propertyService.getPropertyByEircode(eircode));

                    printTenantsWithID(tenantService.getTenantList(eircode));
                    break;
                case 2:
                    System.out.println("\n--------List of the houses--------");
                    System.out.printf("%-10s %8s %-5s %-5s\n","Eircode","Capacity","Cost","Tenants");

                    printAllProperties(propertyService.getAllProperties());
                    break;
                case 3:
                    System.out.println("\n--------List of the houses with spaces--------");
                    System.out.printf("%-10s %8s %-5s %-5s\n","Eircode","Capacity","Cost","Tenants");

                    printAllProperties(propertyService.getAllPropertiesWithSpace());
                    break;
                case 4:
                    System.out.println("\n--------Add a new house--------");
                    System.out.print("What is the house capacity? >> ");
                    int capacity  = Integer.parseInt(input.nextLine());

                    System.out.print("What is the house cost? >> ");
                    int cost = Integer.parseInt(input.nextLine());
                    System.out.print("What is the Eircode of the house? >> ");
                    eircode = input.nextLine();

                    Property prop = propertyService.addNewProperty(capacity, 0, cost, eircode);

                    if(prop == null){
                        System.out.println("Eircode exists");
                    }

                    System.out.println("--- " + prop.getEircode() + " house successfully added.---\n");
                    break;
                case 5:
                    System.out.println("\n--------Add a new tenant and assign that tenant to a household--------");
                    System.out.print("What is the tenant name? >> ");
                    String name  = input.nextLine();
                    System.out.print("What is the tenant email? >> ");
                    String email = input.nextLine();
                    System.out.print("What is the tenant phone number? >> ");
                    String phone = input.nextLine();
                    System.out.print("What is the Eircode to assign the tenant to? >> ");
                    eircode = input.nextLine();

                    if(! propertyService.exists(eircode)){
                        System.out.println("No Eircode found");
                        break;
                    }
                    if(!propertyService.checkPropertyHasSpace(eircode)){
                        System.out.println("No space in property");
                        break;
                    }

                    System.out.println(tenantService.addNewTenant(name,email,phone,eircode).getName() + " tenant added");

                    propertyService.updateOccupation(eircode,1);
                    break;
                case 6:
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("\n--------Delete a household--------");
                    System.out.print("What is the Eircode of the house? >> ");
                    eircode = input.nextLine();

                    if(propertyService.deleteProperty(eircode) != 1){
                        System.out.println("Eircode not found");
                        break;
                    }
                    System.out.println("Household deleted \uD83D\uDE1F");
                    break;
                case 8:
                    System.out.println("\n--------Delete a tenant--------");

                    System.out.print("What is the Eircode of the house? >> ");
                    eircode = input.nextLine();

                    if(! propertyService.exists(eircode)){
                        System.out.println("No Eircode found");
                        break;
                    }

                    printTenantsWithID(tenantService.getTenantList(eircode));
                    System.out.print("What is the tenant id? >> ");

                    int id  = Integer.parseInt(input.nextLine());

                    if(tenantService.removeTenant(id) ==1){
                        System.out.println("Tenant removed.");
                        propertyService.updateOccupation(eircode,-1);
                    }else{
                        System.out.println("Something went wrong");
                    }
                    break;
                case 9:
                    System.out.println("");
                    break;
                case 10:
                    dontExit = false;
                    System.out.println("Bye!");
                    break;
            }
        }
    }

    public static void printTenantsWithID(List<Tenant> tenants){
        for (Tenant t :tenants) {
            System.out.printf("%5s %-20s %-25s %8s %5s\n",t.getId(), t.getName(), t.getEmail(), t.getPhoneNumber(), t.getEircode());
        }
    }

    public static void printProperty(Property property) {
        System.out.printf("%-4s %4s %8s %5s\n",
                property.getCapacity(),
                property.getTenantsOccupy(),
                property.getCost(),
                property.getEircode());
    }

    public static void printAllProperties(List<Property> properties){
        for (Property p : properties) {
            System.out.printf("%-10s %4s %8s %5s\n",
                    p.getEircode(),
                    p.getCapacity(),
                    p.getCost(),
                    p.getTenantsOccupy());
        }
    }
}
