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
public class Curatore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String cognome;
	
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate dataNascita;
	
	@Column
	private String luogoNascita;
	
	@Column
	private String email;
	
	@Column
	private String telefono;
	
	@Column(unique = true)
	private String matricola;
	
	@OneToMany(mappedBy = "curatore", cascade = CascadeType.ALL)
	private List<Collezione> collezioni;
	
	public Curatore() {
		
		this.collezioni = new ArrayList<Collezione>();
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricola == null) ? 0 : matricola.hashCode());
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
		Curatore other = (Curatore) obj;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curatore [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", dateofBirth=" + dataNascita
				+ ", luogoNascita=" + luogoNascita + ", email=" + email + ", telefono=" + telefono + ", matricola="
				+ matricola + ", collezioni=" + collezioni + "]";
	}

	

	

	

}
