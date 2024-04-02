package me.dragon.optimzedlizardac.checks.Aura;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;


public class MultiAura implements PacketListener {

    private int entity,target,lastTarget;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                target = wrapper.getEntityId();

                if (lastTarget != target ){
                    entity++;
                    if (entity > 2){
                        DataStruc.alert("entity= " + entity , (Player) event.getPlayer(),CheckType.AURA,GradeEnum.B);
                    }
                }
                this.lastTarget = target;
            }

        } else if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {
            entity = 0;
        }
    }
}
