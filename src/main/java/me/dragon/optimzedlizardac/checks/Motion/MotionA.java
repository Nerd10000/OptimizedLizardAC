package me.dragon.optimzedlizardac.checks.Motion;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.dragon.optimzedlizardac.checks.Fly.FlyTickManager;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class MotionA implements PacketListener {

     double deltaY,lastDY;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Player player = (Player) event.getPlayer();
        if(WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) && !player.isFlying()){
            deltaY = MovementStruc.y - MovementStruc.lastY;


            boolean isInAir = FlyTickManager.airTicks > 2 && player.getFallDistance() == 0.0;
            double acc = Math.abs( deltaY - lastDY);


            if (acc == 0.0 && isInAir){
                DataStruc.alert("acc= " + acc + " inAir= " + isInAir,player, CheckType.MOTION, GradeEnum.A,3);
            }

            lastDY = deltaY;
        }
    }
}
