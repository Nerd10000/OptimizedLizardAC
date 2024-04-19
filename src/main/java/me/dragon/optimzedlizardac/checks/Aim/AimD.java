package me.dragon.optimzedlizardac.checks.Aim;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class AimD implements PacketListener {

    private  float ratio,lastRatio;
    /*
    Aim Type: D Created by Nerd1000 at 2024.04.19;
    This is inspired by the Vulcan AimY check.But basically its just checks for the difference between the last and the current ratio.
     */

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);

            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                ratio = AimB.ratio;

                if (ratio != lastRatio){
                    float diff = Math.abs(ratio - lastRatio);

                    if (diff > 20){
                        DataStruc.alert("diff= "+ diff, (Player) event.getPlayer(), CheckType.AIM, GradeEnum.D,5);
                    }
                }

                lastRatio = ratio;
            }
        }
    }
}
