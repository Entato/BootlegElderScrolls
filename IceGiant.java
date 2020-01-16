package BootlegElderScrolls;

public class IceGiant extends Boss{
    public IceGiant (){
        super(100, 200, 75, 100, 5, 150, "Ice Giant");
    }

    //if I want to increment name for multiple bosses
    public void setName(String name){
        super.setName(name);
    }
}
