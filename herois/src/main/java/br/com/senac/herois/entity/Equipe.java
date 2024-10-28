package br.com.senac.herois.entity;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @Column
    private String apelido;
    @Column
    private String historiaOrigem;
    @OneToMany(mappedBy = "equipe")
    private Set<SuperHeroi> superHeroi;
    public String getApelido() {
        return apelido;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getHistoriaOrigem() {
        return historiaOrigem;
    }
    public void setHistoriaOrigem(String historiaOrigem) {
        this.historiaOrigem = historiaOrigem;
    }
    public void setSuperHeroi(Set<SuperHeroi> superHeroi) {
        this.superHeroi = superHeroi;
    }
    public Set<SuperHeroi> getsuperHeroi() {
        return superHeroi;
    }
    public void setHerois(Set<SuperHeroi> superHeroi) {
        this.superHeroi = superHeroi;
    }
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
}
