package me.dragon.optimzedlizardac.checks.Strafe;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import me.dragon.optimzedlizardac.checks.Fly.FlyTickManager;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class StrafeA implements PacketListener {
    private  double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            Player player = (Player) event.getPlayer();

            WrapperPlayClientPlayerPosition wrapper = new WrapperPlayClientPlayerPosition(event);
            boolean isOnClientGround = wrapper.isOnGround();

            boolean isInMidAir = FlyTickManager.airTicks > 1;

            float diff = (float) (MovementStruc.yaw  - MovementStruc.lastYaw);

            boolean isInvalid = diff > 40f && diff == 360 && isInMidAir;

            if (isInvalid){
                buffer++;
                if (buffer >2){
                    DataStruc.alert("diff= " + diff + " | buffer= " + buffer,player, CheckType.STRAFE, GradeEnum.A,10);
                    buffer -= 0.20;
                }
                buffer -= 0.97;
            }










        }
    }
}
