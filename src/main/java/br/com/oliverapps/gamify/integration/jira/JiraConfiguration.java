package br.com.oliverapps.gamify.integration.jira;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JiraConfiguration{
        
    private final String JIRA_URI = "https://jira.globalstar.com";
    private final String USERNAME = "walves";
    private final String PASSWORD = "Saturn2018";    
        
    private final Log LOGGER = LogFactory.getLog(getClass()); 
        
    @Bean
    public JiraRestClient getJIRAClient(){
        return new AsynchronousJiraRestClientFactory()
             .createWithBasicHttpAuthentication(URI.create(JIRA_URI), USERNAME, PASSWORD);            
        //    LOGGER.error("Erro na criacao do JiraClient" + e);
    }
    
    
}
