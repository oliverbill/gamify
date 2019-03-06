/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify;

import br.com.oliverapps.gamify.business.XpCalculator;
import br.com.oliverapps.gamify.model.Player;
import br.com.oliverapps.gamify.model.PlayerReport;
import br.com.oliverapps.gamify.model.GameTask;
import br.com.oliverapps.gamify.model.TaskNameEnum;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.TimeTracking;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

@RunWith(JUnit4.class)
public class PlayerReportServiceTest {
    
    private List mockListIssues;            
    private final XpCalculator calc = new XpCalculator();
        
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        
        this.mockListIssues = new ArrayList();
        TimeTracking shortTimeTrackingMock = mock(TimeTracking.class);
        Issue mockedShortIssue = mock(Issue.class);
        when(mockedShortIssue.getTimeTracking()).thenReturn(shortTimeTrackingMock);
        when(shortTimeTrackingMock.getOriginalEstimateMinutes()).thenReturn(465);// 480 = 1d        
        mockListIssues.add(mockedShortIssue);
        
        TimeTracking medTimeTrackingMock = mock(TimeTracking.class);
        Issue mockedMediumIssue = mock(Issue.class);
        when(mockedMediumIssue.getTimeTracking()).thenReturn(medTimeTrackingMock);
        when(medTimeTrackingMock.getOriginalEstimateMinutes()).thenReturn(1200);// 960 = 2d
        mockListIssues.add(mockedMediumIssue);
        
        TimeTracking longTimeTrackingMock = mock(TimeTracking.class);
        Issue mockedLongIssue = mock(Issue.class);
        when(mockedLongIssue.getTimeTracking()).thenReturn(longTimeTrackingMock);
        when(longTimeTrackingMock.getOriginalEstimateMinutes()).thenReturn(1630);// 1440 = 3d
        mockListIssues.add(mockedLongIssue);                
    }
           
    @Test
    public void testCalculateIssueCompleteTasks(){        
        List<GameTask> tasks = calc.calculateIssueCompleteTasks(mockListIssues);
        
        GameTask shortTask = new GameTask(String.valueOf(TaskNameEnum.SHORT_ISSUE_COMPLETE),2);
        GameTask medTask = new GameTask(String.valueOf(TaskNameEnum.MEDIUM_ISSUE_COMPLETE),3);
        GameTask longTask = new GameTask(String.valueOf(TaskNameEnum.LONG_ISSUE_COMPLETE),4);
        
        assertTrue(tasks.size() == 3);
                
        assertThat(shortTask, isIn(tasks));
        assertThat(medTask, isIn(tasks));
        assertThat(longTask, isIn(tasks));
               
        Player p = new Player("Eric Silva");
        PlayerReport r = new PlayerReport(p,tasks);
        assertTrue(r.getCalculatedXp() == 9l);
    }
}
