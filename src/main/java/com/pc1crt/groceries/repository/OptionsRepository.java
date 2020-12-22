/**
 * 
 */
package com.pc1crt.groceries.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pc1crt.groceries.model.Options;

import java.util.UUID;
/**
 * @author paul_
 *
 */
public interface OptionsRepository extends JpaRepository<Options,Integer >{


}
