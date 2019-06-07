package br.com.sistemamedico.config;

import br.com.sistemamedico.controllers.PacienteController;
import br.com.sistemamedico.daos.PacienteDao;
import br.com.sistemamedico.services.PacienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@EnableWebMvc
@ComponentScan(basePackageClasses = {PacienteController.class, PacienteDao.class, PacienteService.class})//Tirei PacienteController.class, pois deu um problema de mapeamento ambíguo depois que rodei um scrpit do jquery
public class ConfiguracaoDaAplicacao extends WebMvcConfigurerAdapter {

    @Bean//Avisa que esse método vai retornar uma classe gerenciada pelo Spring
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");//Evita de ficar mostrando a extensão do arquivo

        return resolver;
    }

    @Bean
    public FormattingConversionService mvcConversionService() {//O Spring espera que o nome do método seja esse, então não use outro nome
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        DateFormatterRegistrar registrador = new DateFormatterRegistrar();
        registrador.setFormatter(new DateFormatter("dd/MM/yyyy"));
        registrador.registerFormatters(conversionService);

        return conversionService;
    }

    //Método sobrescrido da classe extendida que permite o uso do CSS,..., pois libera o acesso a pasta resources
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    //Decide o tipo de negociação(nesse caso ou é html ou json)
    @Bean
    public ViewResolver contentNegociationViewResolver(ContentNegotiationManager manager) {
        List<ViewResolver> viewResolvers = new ArrayList<>();
        viewResolvers.add(internalResourceViewResolver());
        viewResolvers.add(new JsonViewResolver());

        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setViewResolvers(viewResolvers);
        resolver.setContentNegotiationManager(manager);

        return resolver;
    }
}
