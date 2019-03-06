/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 *
 * @author Sony
 */
@Entity(name = "GAME_TASK")
public class GameTask implements Serializable{
    
    private @Id @GeneratedValue Long id;
    private String name;
    private String issueKey;
    private Integer xp;
    private String ps;
    private String limit;
//    @Convert(converter = LocalDateAttributeConverter.class)
//    private LocalDate creationDate;

    public GameTask(){}
    
    public GameTask(String n,Integer xp){
        this.name = n;
        this.xp = xp;
//        this.creationDate = LocalDate.now();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getXp() {
        return xp;
    }

    public void setXp(Integer xp) {
        this.xp = xp;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String issueKey) {
        this.issueKey = issueKey;
    }

//    public LocalDate getCreationDate() {
//        return creationDate;
//    }
            
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GameTask other = (GameTask) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", name=" + name + ", xp=" + xp + ", ps=" + ps + ", limit=" + limit + '}';
    }
}
