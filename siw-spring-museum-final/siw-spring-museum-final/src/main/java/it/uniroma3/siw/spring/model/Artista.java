package it.uniroma3.siw.spring.model;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import lombok.Data;

@Entity
@Data
public class Artista {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String nome;
	
	@Column(nullable=false)
	private String cognome;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascita;
	
	@Column(nullable=false)
	private String luogoNascita;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataMorte;
	
	@Column(nullable=false)
	private String luogoMorte;
	
	@Column(nullable=false)
	private String nazione;
	
	@Column(nullable=true,length=20000)
	private String vita;
	
	@Column(unique = true)
	private String code;
	
	@OneToMany(mappedBy = "autore",cascade=CascadeType.ALL)
	private List<Opera> opere;
	
	public Artista() {
		
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
		Artista other = (Artista) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artista [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dataNascita=" + dataNascita
				+ ", luogoNascita=" + luogoNascita + ", dataMorte=" + dataMorte + ", luogoMorte=" + luogoMorte
				+ ", nazione=" + nazione + ", code=" + code + ", opere=" + opere + "]";
	}
	
	

}
