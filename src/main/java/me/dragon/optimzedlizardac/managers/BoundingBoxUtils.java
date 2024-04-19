package me.dragon.optimzedlizardac.managers;

import org.bukkit.util.BoundingBox;

public class BoundingBoxUtils {

    private BoundingBox box, modifedBox;

    private double xValue, yValue, zValue;

    public BoundingBoxUtils(BoundingBox box) {
        this.box = box;

        yValue = box.getMinY() + box.getMaxY();
        xValue = box.getMaxX() + box.getMinX();
        zValue = box.getMaxZ() + box.getMinZ();
    }

    public void DecreaseSizeWithDouble(double yAxis, double xAxis, double zAxis) {
        box.expand(-xAxis, -yAxis, -zAxis);
    }

}
