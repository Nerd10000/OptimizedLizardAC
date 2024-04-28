package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.LizardAC;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;


public class FlyE implements PacketListener {
    private boolean Found;
    private  double warning;
    private List<Boolean> FoundL = new ArrayList<>(9);
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            Player player = (Player) event.getPlayer();
            double radius = 1;
            Bukkit.getScheduler().runTask(LizardAC.getPlugin(),() -> {
                for (double x = -radius; x <= radius; x++){
                    for (double y = -radius; y <= radius; y++){
                        for (double z = -radius; z <= radius;z++){
                            Location loc = player.getLocation().clone().add(x,y,z);
                            BoundingBox blockBox = player.getWorld().getBlockAt(loc).getBoundingBox();

                            BoundingBox playerBox = player.getBoundingBox().expand(1e-7,1e-7,1e-7);


                            //boolean exempt = player.getFallDistance() > 0.1f; //I will need this for the check!

                            Block block = loc.getBlock();
                            if (playerBox.overlaps(blockBox) && !block.getType().isAir()){
                                Found = true;
                            }else{
                               Found = false;
                            }
                            FoundL.add(Found);
                            boolean FoundTrue = false;
                            for (boolean bool : FoundL){
                                if (bool){
                                    FoundTrue = true;
                                }
                            }
                            if (!FoundTrue){

                                warning++;
                                if (warning > 3){
                                    warning -= 0.2;
                                    DataStruc.alert("No nearby block found! Buffer=" + warning,player,CheckType.FLY,GradeEnum.E,10);
                                }

                            }
                            warning -= 0.98;
                            player.sendMessage("FoundList = " + FoundL + " | IsContainTrue= " + FoundTrue);

                            if (FoundL.size() == 9 || FoundL.size() > 9){
                                FoundL.clear();

                            }



                        }
                    }
                }
            });


        }
    }
}
