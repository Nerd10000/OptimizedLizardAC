package me.dragon.optimzedlizardac.checks.Aura;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class KillauraA implements PacketListener {
    private long FlyingPostTime,InteractPostType;
    private  int buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){
            FlyingPostTime = System.currentTimeMillis();
        } else if (event.getPacketType() == PacketType.Play.Client.INTERACT_ENTITY) {

            long delay =Math.abs(FlyingPostTime - InteractPostType);
            InteractPostType = System.currentTimeMillis();

            if (delay < 15L){ //As in the Flappy Anticheat this should be around 15;
                buffer++;
                if (buffer > 2){
                    DataStruc.alert("delay= "+ delay, (Player) event.getPlayer(), CheckType.AURA, GradeEnum.A,10);
                }
            }
        }
    }
}
