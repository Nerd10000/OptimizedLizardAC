package me.dragon.optimzedlizardac.checks.Aim;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class AimB implements PacketListener {
    private  int hits,swing;
    public static  float ratio;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                hits++;

                if (hits >= 10){

                     ratio =  (hits / (float) swing) *100;
                    Player player = (Player) event.getPlayer();
                    if (ratio > 99.9){
                        DataStruc.alert("ratio= " + ratio, (Player) event.getPlayer(), CheckType.AIM, GradeEnum.B,10);

                    }else {

                    }
                    //player.sendMessage("accuracy= " + ratio);
                    swing = 0;
                    hits = 0;
                }
            }
        } else if (event.getPacketType() == PacketType.Play.Client.ANIMATION) {
            swing++;
        }
    }
}
