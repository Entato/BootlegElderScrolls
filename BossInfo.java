package BootlegElderScrolls;

public class BossInfo{
    public static Boss getBoss(){
        Boss boss = null;

        int stage = Player.getBossCount();

        switch(stage){
            case 2:
                boss = new BaronNashor();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(6));
                break;
            case 3:
                boss = new Reaper();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(7));
                break;
            case 4:
                boss = new IceGiant();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(8));
                break;
                
        }

        return boss;
    }
}