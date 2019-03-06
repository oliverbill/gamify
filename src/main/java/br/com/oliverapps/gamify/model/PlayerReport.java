/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
//@Table(schema = "ADMIN",name = "PLAYER_REPORT")
public class PlayerReport implements Serializable{
 
    @Id @GeneratedValue 
    private Long id;    
    @OneToOne
    private Player player;
    @OneToMany
    private List<GameTask> tasks;
    private Long calculatedXp;
    private LocalDate creationDate = LocalDate.now();

    public PlayerReport() {
    }
            
    public PlayerReport(Player player,List<GameTask> tasks){
        this.player = player;
        this.tasks = tasks;
        this.calculatedXp = this.calculateXp();
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public List<GameTask> getTasks() {
        return tasks;
    }

    public Long getCalculatedXp() {
        return calculatedXp;
    }

    private Long calculateXp(){        
        if(null != tasks){
            return this.tasks.stream()                    
                    .mapToLong(GameTask::getXp).sum();
        }else{
            return 0l;        
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final PlayerReport other = (PlayerReport) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlayerReport{" + "id=" + id + ", player=" + player + ", tasks=" + tasks + ", xp=" + calculatedXp + '}';
    }
    
    
}
