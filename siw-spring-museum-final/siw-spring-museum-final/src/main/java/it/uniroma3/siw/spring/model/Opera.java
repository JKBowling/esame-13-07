package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
public class Opera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String titolo;
	
	@Column
	private String annoRealizzazione;
	
	@Column(nullable=true,length=20000)
	private String descrizione;
	
	@Column(unique = true)
	private String code;
	
	@Column
	private Boolean delMese;
	
	@ManyToOne
	private Collezione collezione;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Artista autore;
	
	public Opera() {}

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
		Opera other = (Opera) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opera [id=" + id + ", titolo=" + titolo + ", annoRealizzazione=" + annoRealizzazione + ", descrizione="
				+ descrizione + ", code=" + code + ", collezione=" + collezione + ", autore=" + autore + "]";
	}

}
