package br.com.senac.herois.entity;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "superHeroi")
public class SuperHeroi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date primeiraApricao;

    @Column
    private String apelido;

    @Column
    private String fraqueza;

    @Column
    private String historiaOrigem;

    @Column
    private String nome;

    @Column
    private String superPoder;

    @ManyToOne
    @JoinColumn(name = "equipe_id", referencedColumnName = "id")
    private Equipe equipe;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Date getPrimeiraApricao() {
            return primeiraApricao;
        }

        public void setPrimeiraApricao(Date primeiraApricao) {
            this.primeiraApricao = primeiraApricao;
        }

        public String getApelido() {
            return apelido;
        }

        public void setApelido(String apelido) {
            this.apelido = apelido;
        }

        public String getFraqueza() {
            return fraqueza;
        }

        public void setFraqueza(String fraqueza) {
            this.fraqueza = fraqueza;
        }

        public String getHistoriaOrigem() {
            return historiaOrigem;
        }

        public void setHistoriaOrigem(String historiaOrigem) {
            this.historiaOrigem = historiaOrigem;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getSuperPoder() {
            return superPoder;
        }

        public void setSuperPoder(String superPoder) {
            this.superPoder = superPoder;
        }

        public Equipe getEquipe() {
            return equipe;
        }

        public void setEquipe(Equipe equipe) {
            this.equipe = equipe;
        }    
}