package me.dragon.optimzedlizardac.managers;

public class StringMethods {

    public String string;
    public  StringBuilder engine;

    public  int length;

    public  StringMethods(String s){
        string = s;

        engine = new StringBuilder(string);
    }

    public void ReplaceFrom(String holder,String placement){
        int index = engine.indexOf(holder);

        while (index != -1){ //Not found

            engine.replace(index,index + holder.length(),placement);
            index = engine.indexOf(holder);
        }
    }
}
