package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Flying;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class FlyB implements PacketListener {

    private  double buffer;
    /*
    FlyB Checks for Y different.
     */
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            int currentTick = FlyTickManager.getCurrentTick();

            double getYOnTick = FlyTickManager.getCurrentY();


            Player player = (Player) event.getPlayer();
            if (FlyTickManager.getDiffY() > 0.42f && !player.isFlying()){
                DataStruc.alert("diff = "+ FlyTickManager.getDiffY() + "currentTIck = " + FlyTickManager.getCurrentTick(), (Player) event.getPlayer(),CheckType.FLY,GradeEnum.B,10);
            }
            //player.sendMessage("currentTick = "+ currentTick + " currentY = " + getYOnTick + " diffY = " + FlyTickManager.getDiffY());


        }
    }
}
