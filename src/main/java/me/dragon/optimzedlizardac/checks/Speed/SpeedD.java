package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class SpeedD implements PacketListener {

    private  double deltaY;
    private  int buffer;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            deltaY = MovementStruc.y - MovementStruc.lastY;
            Player player = (Player) event.getPlayer();
            if (deltaY > 10 && player.getFallDistance() == 0.0f && !player.isFlying()){
                buffer++;
                if (buffer > 1){
                    DataStruc.alert("deltaY= "+ deltaY + " buffer= "+ buffer, (Player) event.getPlayer(), CheckType.SPEED, GradeEnum.D,10);
                }
            }else {
                buffer--;
            }

            //this.lastDeltaY = deltaY;
        }
    }
}
