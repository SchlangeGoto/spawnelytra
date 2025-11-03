package dev.kkazi.spawnelytra.mixin;

import dev.kkazi.spawnelytra.SpawnElytraListener;
import net.minecraft.network.protocol.game.ServerboundPlayerAbilitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.level.GameType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerMixin {

    @Shadow public ServerPlayer player;

    @Inject(method = "handlePlayerAbilities", at = @At("HEAD") , cancellable = true)
    private void onHandlePlayerAbilities(ServerboundPlayerAbilitiesPacket packet, CallbackInfo ci) {
        boolean tryToFly = packet.isFlying() && !player.getAbilities().flying;

        if (tryToFly) {
            if (player.gameMode() == GameType.SURVIVAL || player.gameMode() == GameType.ADVENTURE) {
                ci.cancel();
                SpawnElytraListener.getInstance().tryToFly(player);
            }
        }
    }

}
