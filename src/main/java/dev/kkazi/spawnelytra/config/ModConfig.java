package dev.kkazi.spawnelytra.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.world.level.Level;


@Config(name = "spawnelytra")
public class ModConfig implements ConfigData {
    @Comment("Value the velocity of the player should get multiplied with when boosting (3 by default)")
    public int boostStrength = 3;

    @Comment("Should the boost be enabled (true by default)")
    public boolean boostEnabled = true;

    @Comment("!!!!!DOES NOT WORK RIGHT NOW Set spawn radius automaticaly, this uses the spawn protection size (false by default)")
    public boolean setRadiusAuto = false;

    @Comment("Set spawn radius")
    public int spawnRadius = 100;


    @Override
    public String toString() {
        return "ModConfig{" +
                "boostStrength=" + boostStrength +
                ", boostEnabled=" + boostEnabled +
                ", setRadiusAuto=" + setRadiusAuto +
                ", spawnRadius=" + spawnRadius +
                '}';
    }
}



