package dev.kkazi.spawnelytra;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class SpawnElytraMod implements ModInitializer {
    private int tickCounter = 0;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTED.register((MinecraftServer server) -> {
            ServerLevel world = server.overworld();

            SpawnElytraListener listener = SpawnElytraListener.init(
                    6,      // multiplyValue
                    100,    // spawnRadius
                    true,   // boostEnabled
                    world,
                    "Press %key% to boost!"
            );

            ServerTickEvents.END_SERVER_TICK.register(server1 -> {
                tickCounter++;
                if (tickCounter >= 3) {
                    listener.run();
                    tickCounter = 0;
                }
            });

            ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
                if (entity instanceof ServerPlayer player) {
                    if (listener.getFlying().contains(player.getUUID())) {
                        if (source.is(net.minecraft.world.damagesource.DamageTypes.FALL) || source.is(net.minecraft.world.damagesource.DamageTypes.FLY_INTO_WALL)) {
                            return false; // cancel damage
                        }
                    }
                }
                return true; // allow other damage
            });
        });


    }
}
