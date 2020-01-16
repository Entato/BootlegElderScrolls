package BootlegElderScrolls;

public class IceGiant extends Boss{
    public IceGiant (){
        super(125, 350, 100, 5, 50, "Ice Giant");
    }

    //if I want to increment name for multiple bosses
    public void setName(String name){
        super.setName(name);
    }
}
