/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.oliverapps.gamify.repository;

import br.com.oliverapps.gamify.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sony
 */
public interface PlayerRepository extends JpaRepository<Player, String>{
    
}
