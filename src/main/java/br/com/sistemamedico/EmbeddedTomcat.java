package br.com.sistemamedico;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

public class EmbeddedTomcat {

    public static void main(String[] args) throws LifecycleException, ServletException {
        String contextPath = "/";
        String webappDir = new File("target/sistemamedico").getAbsolutePath();

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("src/main/java");
        tomcat.setPort(8080);

        tomcat.addWebapp(contextPath, webappDir);

        tomcat.start();
        tomcat.getServer().await();



    }
}
