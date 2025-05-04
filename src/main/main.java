package main;

import entities.Knight;
import game.Battle;
import simulator.BattleManager;

import java.util.List;

public class main {

    public static void main(String[] args) {
        Knight knight1 = new Knight();
        Knight knight2 = new Knight();

        Battle battle = new Battle(List.of(knight1), List.of(knight1));

        BattleManager battleManager = new BattleManager(battle);
        battleManager.printBattleState();
    }
}
