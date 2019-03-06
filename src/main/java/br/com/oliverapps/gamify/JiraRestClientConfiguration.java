package br.com.oliverapps.gamify;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import java.net.URI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JiraRestClientConfiguration {
    
    private final String JIRA_URI = "https://jira.globalstar.com";
    private final String USERNAME = "walves";
    private final String PASSWORD = "Saturn2018";
    
    @Bean
    JiraRestClient getJiraRestClient() {        
        return new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication
                    (URI.create(JIRA_URI), USERNAME, PASSWORD);
    }
    
    @Bean
    RestTemplate getRestClient(){
        return new RestTemplate();
    }
}
