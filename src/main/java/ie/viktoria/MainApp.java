package ie.viktoria;

//import ie.viktoria.config.ConfigH2;
import ie.viktoria.config.ConfigH2;
import ie.viktoria.service.ITenantService;
import ie.viktoria.service.TenantServiceImplementation;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

public class MainApp {
    public static void main(String[] args) {

//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigH2.class);
        ITenantService tenantService = context.getBean(TenantServiceImplementation.class);
//        System.out.println("tenants added" , tenantService.addTheTenant());
        System.out.println("There are " + tenantService.countTheTenants() + " tenants in the database.");

    }
}
