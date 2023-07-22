package br.com.phc.brasileiraoapi.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Equipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Equipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name="equipe_id")
	private long id;
    
	@Column(name="equipe_nome")
	private String nomeEquipeString;	
}








