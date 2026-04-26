package dano.ZonaFit;

import com.formdev.flatlaf.FlatDarculaLaf;
import dano.ZonaFit.gui.Form;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {
    public static void main(String[] args) {
        FlatDarculaLaf.setup();

        ConfigurableApplicationContext contextoSpring = new SpringApplicationBuilder(ZonaFitSwing.class).headless(false).web(WebApplicationType.NONE).run(args);

        SwingUtilities.invokeLater(() -> {
            Form forma = contextoSpring.getBean(Form.class);
            forma.setVisible(true);
        });
    }
}
