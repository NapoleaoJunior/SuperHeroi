package br.com.senac.herois.controller;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.senac.herois.entity.SuperHeroi;
import br.com.senac.herois.repository.SuperHeroiRepository;

@RestController
public class SuperHeroiController {
    private SuperHeroiRepository superHeroiRepository;
    
    public SuperHeroiController(SuperHeroiRepository superHeroiRepository) {
        this.superHeroiRepository = superHeroiRepository;
    }
     @GetMapping("/superHeroi")
    public ResponseEntity<?> getDadosSuperHeroi() {
        return new ResponseEntity<>(superHeroiRepository.findAll(), HttpStatus.OK);
    }
     @GetMapping("superHeroi/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return new ResponseEntity<>(superHeroiRepository.findById(id), HttpStatus.OK);
    }
    @PostMapping("/superHeroi")
    public ResponseEntity<?> salvaSuperHeroi(@RequestBody SuperHeroi entity) {
        SuperHeroi superHeroiSalvo;
        try {
            superHeroiSalvo = superHeroiRepository.save(entity);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar o super heroi", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<SuperHeroi>(superHeroiSalvo, HttpStatus.OK);
    }
     @PutMapping("superHeroi/{id}")
    public ResponseEntity<?> atualizaSuperHeroi(@PathVariable int id, @RequestBody SuperHeroi entity) {
        Optional<SuperHeroi> superHeroiAtualizar = superHeroiRepository.findById(id);
        SuperHeroi sh = null;
        if (superHeroiAtualizar.isPresent()) {
            sh = superHeroiAtualizar.get();
            sh.setNome(entity.getNome());
            sh.setPrimeiraApricao(entity.getPrimeiraApricao());
            sh.setApelido(entity.getApelido());
            sh.setSuperPoder(entity.getSuperPoder());
            sh.setFraqueza(entity.getFraqueza());
            sh.setHistoriaOrigem(entity.getHistoriaOrigem());
            try {
                sh = superHeroiRepository.save(sh);
            } catch (Exception e) {
                return new ResponseEntity<String>("erro ao atualizar o super heroi", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<SuperHeroi>(sh, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("Super Heroi nao encontrado", HttpStatus.BAD_REQUEST);
        }
    }
@DeleteMapping("/superHeroi/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable int id) {
        Optional<SuperHeroi> superHeroiExcluir = superHeroiRepository.findById(id);
        
        if (superHeroiExcluir.isPresent()) {
            try {
                superHeroiRepository.delete(superHeroiExcluir.get());
                return new ResponseEntity<>("super heroi excluído com sucesso", HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao excluir o super heroi", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("super heroi não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}

