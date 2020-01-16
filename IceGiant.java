package BootlegElderScrolls;

public class IceGiant extends Boss{
    public IceGiant (){
        super(90, 350, 100, 100, 5, 25, "Ice Giant");
    }

    //if I want to increment name for multiple bosses
    public void setName(String name){
        super.setName(name);
    }
}
