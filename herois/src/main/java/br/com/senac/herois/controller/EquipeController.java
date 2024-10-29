package br.com.senac.herois.controller;
import org.apache.el.stream.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.senac.herois.entity.Equipe;
import br.com.senac.herois.repository.EquipeRepository;

@RestController
public class EquipeController {
    private EquipeRepository equipeRepository;
    public EquipeController(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }
    @GetMapping("/equipe")
    public ResponseEntity<?> getEquipes() {        
        return new ResponseEntity<>(equipeRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/equipe")
    public ResponseEntity<?> salvaSuperHeroi(@RequestBody Equipe entity) {
        Equipe equipeSalvo;
        try {
            equipeSalvo = equipeRepository.save(entity);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao salvar o Heroi", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Equipe>(equipeSalvo, HttpStatus.OK);
    }
     @PutMapping("equipe/{id}")
    public ResponseEntity<?> atualizaEquipe(@PathVariable int id, @RequestBody Equipe entity) {
        java.util.Optional<Equipe> equipeAtualizar = equipeRepository.findById(id);
        Equipe e = null;
        if (equipeAtualizar.isPresent()) {
             e = equipeAtualizar.get();
            e.setNome(entity.getNome());
            e.setHistoriaOrigem(entity.getHistoriaOrigem());
            try {
                e = equipeRepository.save(e);
            } catch (Exception ex) {
                return new ResponseEntity<String>("erro ao atualizar o Heroi", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<Equipe>(e, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("produto nao encontrado", HttpStatus.BAD_REQUEST);
        }
    }
}
