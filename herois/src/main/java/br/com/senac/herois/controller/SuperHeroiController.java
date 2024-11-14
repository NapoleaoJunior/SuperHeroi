package br.com.senac.herois.controller;

import java.util.List;
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

    // Endpoint para buscar todos os super-heróis sem dados recursivos
    @GetMapping("/superHeroi")
    public ResponseEntity<List<SuperHeroi>> getDadosSuperHeroi() {
        List<SuperHeroi> superHerois = superHeroiRepository.findAll();

        // A partir daqui, podemos modificar os dados para garantir que não haja dados recursivos
        for (SuperHeroi hero : superHerois) {
            if (hero.getEquipe() != null) {
                // Evitar a inclusão de heróis da equipe na resposta, mantendo apenas os dados essenciais
                hero.getEquipe().setSuperHeroi(null);
            }
        }

        return new ResponseEntity<>(superHerois, HttpStatus.OK);
    }

    // Endpoint para buscar um super-herói por ID
    @GetMapping("/superHeroi/{id}")
    public ResponseEntity<Object> getById(@PathVariable int id) {
        Optional<SuperHeroi> superHeroi = superHeroiRepository.findById(id);
    
        if (superHeroi.isPresent()) {
            SuperHeroi hero = superHeroi.get();
            if (hero.getEquipe() != null) {
                // Evitar a inclusão de heróis da equipe na resposta
                hero.getEquipe().setSuperHeroi(null);
            }
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } else {
            // Retornando uma mensagem de erro como resposta
            return new ResponseEntity<>("Super-herói não encontrado", HttpStatus.NOT_FOUND);
        }
    }
    

    // Endpoint para buscar super-heróis pelo nome
    @GetMapping("/superHeroi/nome/{nome}")
    public ResponseEntity<List<SuperHeroi>> getByNome(@PathVariable String nome) {
        List<SuperHeroi> superHerois = superHeroiRepository.findByNomeLike("%" + nome + "%");

        for (SuperHeroi hero : superHerois) {
            if (hero.getEquipe() != null) {
                // Evitar a inclusão de heróis da equipe na resposta
                hero.getEquipe().setSuperHeroi(null);
            }
        }

        return new ResponseEntity<>(superHerois, HttpStatus.OK);
    }

    // Endpoint para buscar super-heróis pelo apelido
    @GetMapping("/superHeroi/apelido/{apelido}")
    public ResponseEntity<List<SuperHeroi>> getByApelido(@PathVariable String apelido) {
        List<SuperHeroi> superHerois = superHeroiRepository.findByNomeLike("%" + apelido + "%");

        for (SuperHeroi hero : superHerois) {
            if (hero.getEquipe() != null) {
                // Evitar a inclusão de heróis da equipe na resposta
                hero.getEquipe().setSuperHeroi(null);
            }
        }

        return new ResponseEntity<>(superHerois, HttpStatus.OK);
    }

    // Endpoint para salvar um super-herói
    @PostMapping("/superHeroi")
    public ResponseEntity<?> salvaSuperHeroi(@RequestBody SuperHeroi entity) {
        SuperHeroi superHeroiSalvo;
        try {
            superHeroiSalvo = superHeroiRepository.save(entity);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao salvar o super-herói", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(superHeroiSalvo, HttpStatus.OK);
    }

    // Endpoint para atualizar um super-herói
    @PutMapping("/superHeroi/{id}")
    public ResponseEntity<?> atualizaSuperHeroi(@PathVariable int id, @RequestBody SuperHeroi entity) {
        Optional<SuperHeroi> superHeroiAtualizar = superHeroiRepository.findById(id);
        if (superHeroiAtualizar.isPresent()) {
            SuperHeroi sh = superHeroiAtualizar.get();
            sh.setNome(entity.getNome());
            sh.setPrimeiraApricao(entity.getPrimeiraApricao());
            sh.setApelido(entity.getApelido());
            sh.setSuperPoder(entity.getSuperPoder());
            sh.setFraqueza(entity.getFraqueza());
            sh.setHistoriaOrigem(entity.getHistoriaOrigem());

            try {
                sh = superHeroiRepository.save(sh);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao atualizar o super-herói", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(sh, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Super-herói não encontrado", HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint para excluir um super-herói
    @DeleteMapping("/superHeroi/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable int id) {
        Optional<SuperHeroi> superHeroiExcluir = superHeroiRepository.findById(id);

        if (superHeroiExcluir.isPresent()) {
            try {
                superHeroiRepository.delete(superHeroiExcluir.get());
                return new ResponseEntity<>("Super-herói excluído com sucesso", HttpStatus.NO_CONTENT);
            } catch (Exception e) {
                return new ResponseEntity<>("Erro ao excluir o super-herói", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Super-herói não encontrado", HttpStatus.NOT_FOUND);
        }
    }
}
