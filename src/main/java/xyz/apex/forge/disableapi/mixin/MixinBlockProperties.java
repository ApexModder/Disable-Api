package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.BlockPropertiesExtended;

import java.util.function.Predicate;

@Mixin(BlockBehaviour.Properties.class)
public class MixinBlockProperties implements BlockPropertiesExtended
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @Override
    public BlockBehaviour.Properties requiredGameContext(Predicate<GameContext> predicate)
    {
        DisableApi$requiredGameContext = predicate;
        return (BlockBehaviour.Properties) (Object) this;
    }

    @Override
    public Predicate<GameContext> getRequiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }
}
