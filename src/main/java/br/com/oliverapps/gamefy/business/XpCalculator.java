/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamefy.business;

import br.com.oliverapps.gamefy.model.GameTask;
import br.com.oliverapps.gamefy.model.TaskNameEnum;
import com.atlassian.jira.rest.client.api.domain.Issue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sony
 */
public class XpCalculator {
        
    public XpCalculator(){
        
    }
    
    public List<GameTask> calculateIssueCompleteTasks(Iterable<Issue> issues){
        double estimatesInHours = 0.0;
        int xpCount = 0;
        List<GameTask> tasks = new ArrayList<>();
                
        for (Issue issue : issues) {
            GameTask t = null;
            if(issue.getTimeTracking().getOriginalEstimateMinutes() != null){
                estimatesInHours = issue.getTimeTracking().getOriginalEstimateMinutes() / 60.0;
                if (estimatesInHours <= 8){ 
                    xpCount = 2;
                    t = new GameTask(String.valueOf(TaskNameEnum.SHORT_ISSUE_COMPLETE),xpCount);
                }else if (estimatesInHours < 24){
                    xpCount = 3;
                    t = new GameTask(String.valueOf(TaskNameEnum.MEDIUM_ISSUE_COMPLETE),xpCount);
                }else{
                    xpCount = 4;
                    t = new GameTask(String.valueOf(TaskNameEnum.LONG_ISSUE_COMPLETE),xpCount);
                }
                t.setIssueKey(issue.getKey());
                tasks.add(t);
            }
        }        
        return tasks;
    }
}
