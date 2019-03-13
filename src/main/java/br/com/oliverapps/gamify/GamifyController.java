/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify;

import br.com.oliverapps.gamify.model.GameTask;
import br.com.oliverapps.gamify.model.Player;
import br.com.oliverapps.gamify.model.PlayerReport;
import br.com.oliverapps.gamify.service.PlayerReportService;
import com.atlassian.jira.rest.client.api.JiraRestClient;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Sony
 */
@RestController
public class GamifyController {
    
    @Autowired
    private PlayerReportService reportService;
    
    @Autowired
    RestTemplate RESTTemplate;
    
    @Autowired
    JiraRestClient JIRAClient;
       
    @RequestMapping(value = "/players")
    public List<Player> players() {
        List<Player> players = reportService.allPlayers();
        return players;
    }
    
    @RequestMapping(value = "/tasks")
    public List<GameTask> tasks() {
        List<GameTask> tasks = reportService.allTasks();
        return tasks;
    }
    
    @RequestMapping(value = "/reports")
    public List<PlayerReport> allPlayersReport() {
        List<PlayerReport> reports = reportService.allReports();
        return reports;
    }
                
    @GetMapping(value = "/events/check/{nickName}/{taskName}/{sprints}")
    public PlayerReport getPlayerReport(@RequestParam(value="nickName") String nickName,
            @RequestParam(value="taskName") String taskName, @RequestParam(value="sprints") String sprints)
    {
        return reportService.generatePlayerReportByJIRAIssues(nickName,taskName,sprints);                
    }
       

}
