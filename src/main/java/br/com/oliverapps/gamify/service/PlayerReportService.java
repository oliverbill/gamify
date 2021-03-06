/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.service;

import br.com.oliverapps.gamify.model.Player;
import br.com.oliverapps.gamify.model.PlayerReport;
import br.com.oliverapps.gamify.model.GameTask;
import java.util.List;


public interface PlayerReportService {
    public List<GameTask> allTasks();
    public List<Player> allPlayers();
    public List<PlayerReport> allReports();
    public PlayerReport generatePlayerReportByJIRAIssues(String nickName,String taskName,String sprints);
    public List<GameTask> generateIssueCompleteTasks(String nickName,String sprints);
}
