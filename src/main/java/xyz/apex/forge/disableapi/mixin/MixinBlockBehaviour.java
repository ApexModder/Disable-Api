package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.forge.disableapi.api.DisableableElement;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.BlockPropertiesExtended;

import java.util.function.Predicate;

@Mixin(BlockBehaviour.class)
public abstract class MixinBlockBehaviour implements DisableableElement
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @Override
    public Predicate<GameContext> requiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void DisableApi$init(BlockBehaviour.Properties properties, CallbackInfo ci)
    {
        DisableApi$requiredGameContext = BlockPropertiesExtended.of(properties).getRequiredGameContext();
    }
}
