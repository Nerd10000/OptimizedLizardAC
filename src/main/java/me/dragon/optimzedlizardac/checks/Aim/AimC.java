package me.dragon.optimzedlizardac.checks.Aim;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class AimC implements PacketListener {
    private  Float ratio,lastRatio;
    private  double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

             ratio = AimB.ratio;


             if (ratio != 0.0 || !ratio.isInfinite()){

                 float Diff = Math.abs(ratio - lastRatio);


                 if (Diff > 35.9){
                     buffer++;
                     if (buffer > 1){
                         DataStruc.alert("Diff= "+ Diff, (Player) event.getPlayer(), CheckType.AIM, GradeEnum.C,3);
                     }
                 }
             }



            lastRatio = ratio;
        }
    }
}
