package xyz.jabwin.myVertX;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
@SpringBootApplication
public class Bootstrap
{
  public static void main(String[] args)
  {
    SpringApplication app = new SpringApplication(Bootstrap.class);
    app.setWebApplicationType(WebApplicationType.NONE);
    app.run(args);
  }
}
