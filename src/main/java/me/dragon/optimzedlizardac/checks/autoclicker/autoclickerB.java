package me.dragon.optimzedlizardac.checks.autoclicker;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class autoclickerB implements PacketListener {
    private List<Long> samples = new ArrayList<>(3);
    private long clickTime,lastClickTime;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {



        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            //Get the current Millis;
            clickTime = System.currentTimeMillis();

            long delay = Math.abs(clickTime - lastClickTime); //Get the delay between 2 samples

            //Clear the list if it is full;
            if (samples.size() == 3){
                samples.clear();
            }
            //Give samples if the size is <= than 3;
            if (samples.size() != 3){
                samples.add(delay);
            }
            if (getDuplicates() > 2){
                DataStruc.alert("duplicates= " +  getDuplicates(), (Player) event.getPlayer(), CheckType.AUTOCLICKER, GradeEnum.B);
            }
            //Get the last 'clickTime'
            this.lastClickTime = clickTime;

        }
    }
    //Get the duplicates of the sample list; Because some auto clicker have timings so every 100millis hit one.
    public int getDuplicates(){
        int duplicates = 0;
        //we check the list for any duplicates and if we found we give duplicates+1;
        for (Long e : samples){
            if (samples.contains(e)){
                duplicates++;
            }else {

            }

        }
        //We return the duplicates;
        return duplicates;
    }


}
