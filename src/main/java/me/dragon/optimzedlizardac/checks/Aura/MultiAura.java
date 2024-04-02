package me.dragon.optimzedlizardac.checks.Aura;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;


public class MultiAura extends DataStruc implements PacketListener {
    public MultiAura() {
        super(CheckType.AURA, GradeEnum.B);
    }
    private int entity;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY){
            WrapperPlayClientInteractEntity wrapper = new WrapperPlayClientInteractEntity(event);
            if (wrapper.getAction() == WrapperPlayClientInteractEntity.InteractAction.ATTACK){
                entity++;
                if (entity > 1){
                    DataStruc.alert("entity= " + entity, (Player) event.getPlayer());
                }
            }

        } else if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {
            entity = 0;
        }
    }
}
