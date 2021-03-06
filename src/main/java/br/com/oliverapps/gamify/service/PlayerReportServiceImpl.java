/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.service;

import br.com.oliverapps.gamify.business.XpCalculator;
import br.com.oliverapps.gamify.integration.jira.JiraConfiguration;
import br.com.oliverapps.gamify.model.Player;
import br.com.oliverapps.gamify.model.PlayerReport;
import br.com.oliverapps.gamify.model.GameTask;
import br.com.oliverapps.gamify.repository.PlayerRepository;
import br.com.oliverapps.gamify.repository.ReportRepository;
import br.com.oliverapps.gamify.repository.TaskRepository;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import io.atlassian.util.concurrent.Promise;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerReportServiceImpl implements PlayerReportService{
    
    @Autowired
    private TaskRepository taskRepo;    
    @Autowired
    private PlayerRepository playerRepo;    
    @Autowired
    private ReportRepository reportRepo;
    @Autowired
    private JiraRestClient JIRAClient;    
    @Autowired
    private XpCalculator calc;
    
    @Autowired
    private JiraRestClient jiraClient;
    
    private final String GET_ISSUES_URI_BASE = "http://localhost:8080/rest/api/2/search?jql=";
    
    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getSimpleName());
        
    public PlayerReportServiceImpl(JiraRestClient c,ReportRepository reportRepo,
            PlayerRepository pRepo,TaskRepository tRepo)
    {
        this.jiraClient = c;
        this.reportRepo = reportRepo;
        this.playerRepo = pRepo;
        this.taskRepo = tRepo;
    }
    
    private GameTask findTask(String taskId){
        GameTask t = null;
        try{
            t = taskRepo.findById(Long.valueOf(taskId)).get();
        }catch(NoSuchElementException e){
            if (t == null)
                LOGGER.error("task not found: " + taskId);
        }
        return t;
    }
    
    private Player findPlayer(String nickName){
        Player p = null;
        try{
            p = playerRepo.findById(nickName).get();                        
        }catch(NoSuchElementException e){
            if (p == null)
                LOGGER.error("player not found: " + nickName);
        }
        return p;
    }
        
    public List<GameTask> allTasks(){
        return taskRepo.findAll();
    }
    
    public List<Player> allPlayers(){
        return playerRepo.findAll();
    }
    
    public List<PlayerReport> allReports(){
        return reportRepo.findAll();
    }
    
    public PlayerReport generatePlayerReportByJIRAIssues(String nickName,String taskName,String sprints)
    {       
        List<GameTask> tasks = new ArrayList<>();
                
        tasks.addAll(generateIssueCompleteTasks(nickName,sprints));
        
        PlayerReport report = new PlayerReport(findPlayer(nickName), tasks);
        return reportRepo.save(report);//save tasks and players 
    }
    
    public List<GameTask> generateIssueCompleteTasks(String nickName,String sprints){
        String prwJQL = String.format("assignee=1$ and status in (2$) and sprint in($3)", nickName,"Dev Test Complete",sprints);
        Promise<SearchResult> searchJqlPromise = JIRAClient.getSearchClient().searchJql(prwJQL);
        
        Iterable<Issue> issues = searchJqlPromise.claim().getIssues();
        List<GameTask> tasks = calc.calculateIssueCompleteTasks(issues);
        return tasks;
    }
    
//    public List<GameTask> generateIssueCompleteTasks(String nickName,String sprints){
//        RestTemplate restClient = new RestTemplate();
//        
//        String prwJQL = String.format("assignee=1$ and status in (2$) and sprint in($3)", 
//                nickName,"Dev Test Complete",sprints);
//        
//        final String GET_ISSUES_URI = GET_ISSUES_URI_BASE + prwJQL;        
//        
//        ResponseEntity<String> jsonResp = restClient.getForEntity(GET_ISSUES_URI, String.class);
//        
//        //Iterable<Issue> issues = searchJqlPromise.claim().getIssues();
//        
////        ObjectMapper mapper = new ObjectMapper();
////        JsonNode root = mapper.readTree(response.getBody());
////        JsonNode name = root.path("name");
//                        
//        List<GameTask> tasks = calc.calculateIssueCompleteTasks(issues);
//        return tasks;
//    }    
}
