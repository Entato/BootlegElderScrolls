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
            case 5:
                boss = new OgreLord();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(9));
                break;
            case 6:
                boss = new DungeonKeeper();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(10));
                break;
            case 7:
                boss = new Haunted();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(11));
                break;
            case 8:
                boss = new Colossus();
                Visuals.getTeam2Sprites().add(Visuals.getSprites().get(12));


        }

        return boss;
    }
}