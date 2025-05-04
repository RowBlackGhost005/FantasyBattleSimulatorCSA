package game;

import entities.Entity;

import java.util.List;

public class Battle {

    private List<Entity> team1;
    private List<Entity> team2;

    public Battle(List<Entity> team1, List<Entity> team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public List<Entity> getTeam1() {
        return team1;
    }

    public List<Entity> getTeam2() {
        return team2;
    }



}
