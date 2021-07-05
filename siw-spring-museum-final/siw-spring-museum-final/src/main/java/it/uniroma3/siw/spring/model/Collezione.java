package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data 
public class Collezione {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String descrizione;
	
	@Column
	private String code;
	
	@ManyToOne
	private Curatore curatore;
	
	@OneToMany(mappedBy = "collezione", cascade = CascadeType.ALL)
	private List<Opera> opere;
	
	public Collezione() {
		
		this.opere = new ArrayList<Opera>();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collezione other = (Collezione) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Collezione [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", code=" + code
				+ ", curatore=" + curatore + ", opere=" + opere + "]";
	}

	

	

	
	
	
	
	
	
	

}
