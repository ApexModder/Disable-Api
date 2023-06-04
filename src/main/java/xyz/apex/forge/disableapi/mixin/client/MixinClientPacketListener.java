package xyz.apex.forge.disableapi.mixin.client;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.protocol.game.ClientboundUpdateEnabledFeaturesPacket;
import net.minecraft.world.flag.FeatureFlagSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.impl.GameContextImpl;

@Mixin(ClientPacketListener.class)
public abstract class MixinClientPacketListener
{
    @Shadow public abstract FeatureFlagSet enabledFeatures();

    @Shadow public abstract RegistryAccess registryAccess();

    /**
     * @author ApexStudios
     * @reason Replaced with {@link GameContext#hasFeatures(FeatureFlagSet)}
     */
    @Overwrite
    public boolean isFeatureEnabled(FeatureFlagSet features)
    {
        return GameContext.get().hasFeatures(features);
    }

    @Inject(
            method = "handleEnabledFeatures",
            at = @At("TAIL")
    )
    private void DisableApi$handleEnabledFeatures(ClientboundUpdateEnabledFeaturesPacket packet, CallbackInfo ci)
    {
        // reload the context with upto date information.
        GameContextImpl.INSTANCE.reload(this::enabledFeatures, this::registryAccess);
    }
}
