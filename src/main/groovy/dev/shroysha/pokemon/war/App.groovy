package dev.shroysha.pokemon.war

import dev.shroysha.pokemon.ejb.*
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static dev.shroysha.pokemon.ejb.PokemonEntityScanTag.*

@SpringBootApplication
@EntityScan(basePackageClasses = PokemonEntityScanTag.class)
@RestController
class App {

    static void main(String[] args) {
        SpringApplication.run(App.class, args)
    }

    private PokemonBattleRepo pokemonBattleRepo
    private PokemonTrainerRepo pokemonTrainerRepo

    @RequestMapping(TRAINER_CREATE)
    PokemonTrainer newTrainer(String trainerName) {
        PokemonTrainer newTrainer = new PokemonTrainer(trainerName)
        pokemonTrainerRepo.save(newTrainer)
        return newTrainer
    }

    @RequestMapping(BATTLE_CREATE)
    PokemonBattle newBattle(PokemonTrainer trainer) {
        PokemonBattle battle = null

        for (PokemonBattle nextBattle : pokemonBattleRepo.findAll()) {
            if (!nextBattle.isBattleFull()) {
                battle = nextBattle
            }
        }
        if (battle == null) {
            battle = new PokemonBattle()
            battle.setTrainer1(trainer)
        } else {
            battle.setTrainer2(trainer)
        }
        pokemonBattleRepo.save(battle)
        return battle
    }

    @RequestMapping(BATTLE_MOVE)
    void doBattleMove(PokemonTrainer trainer, PokemonBattle.PokemonBattleMove move, PokemonBattle battle) {
//        battle.registerNextMove(trainer, move);
//        if (battle.bothMovesRegistered()) {
//            battle.doMoves();
//        }
    }

}
