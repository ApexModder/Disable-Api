package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.EntityTypeExtended;

import java.util.function.Predicate;

@Mixin(EntityType.class)
public abstract class MixinEntityType<T extends Entity> implements EntityTypeExtended<T>
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @Override
    public Predicate<GameContext> requiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }

    @Override
    public void setRequiredGameContext(Predicate<GameContext> requiredGameContext)
    {
        DisableApi$requiredGameContext = requiredGameContext;
    }
}
