/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.model;

public enum TaskNameEnum {
    ISSUE_COMPLETE("to complete an issue with successful PRW in Dev Test Complete status",2),
    
    SHORT_ISSUE_COMPLETE("to complete an issue with successful PRW in Dev Test Complete status",2),
    MEDIUM_ISSUE_COMPLETE("to complete an issue with successful PRW in Dev Test Complete status",3),
    LONG_ISSUE_COMPLETE("to complete an issue with successful PRW in Dev Test Complete status",4),
    SPEAK_ENGLISH("to speak english for a whole day",1),
    FOLLOW_DEV_PROCESS("to log hours daily, to update status, to put the resolution, "
            + "to add deployment instructions on Confluence and all the activities "
            + "demanded by the Globalstar dev process",1),
    HELP_COLlEGUE("to help a colLeague to solve an issue",3);
    
    private String name;
    private Integer xp;
    
    private TaskNameEnum(String n,Integer xp){
        this.name = n;
        this.xp = xp;
    }

    public String getName() {
        return name;
    }

    public Integer getXp() {
        return xp;
    }
    
    
    
    public static TaskNameEnum guessTaskName(String n){
        for(TaskNameEnum t : TaskNameEnum.values()){
            if(t.name.contains(n)){
                return t;
            }
        }
        return null;
    }
}
