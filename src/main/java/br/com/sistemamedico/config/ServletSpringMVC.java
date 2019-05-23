package br.com.sistemamedico.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

//Herdamos de uma classe que inicializa o servlet do Spring
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    //Especifica as classes de configuração que teremos na aplicação
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {ConfiguracaoDaAplicacao.class, ConfiguracaoJPA.class};
    }

    //Especifica qual o urls o spring deve mapear, que nesse caso é tudo que vem depois do /
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        //Filtro do spring que trata o encode
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");

        return new Filter[] {encodingFilter};
    }
}
