package simulator;

import game.Battle;

public class BattleManager {

    Battle battle;

    public BattleManager(Battle battle) {
        this.battle = battle;
    }

    public void printBattleState(){
        System.out.println("Team 1:");
        battle.getTeam1().forEach(System.out::println);
        System.out.println("Team 2:");
        battle.getTeam2().forEach(System.out::println);
    }


}
