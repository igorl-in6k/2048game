package game2048.console.run;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "XMLApplicationContext.xml");

        GameController gc = (GameController) context.getBean("gameController");
        gc.runApp();
    }
}
