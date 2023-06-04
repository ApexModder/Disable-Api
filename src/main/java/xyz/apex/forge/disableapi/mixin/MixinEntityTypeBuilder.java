package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.EntityTypeBuilderExtended;
import xyz.apex.forge.disableapi.api.extensions.EntityTypeExtended;

import java.util.function.Predicate;

@Mixin(EntityType.Builder.class)
public class MixinEntityTypeBuilder<T extends Entity> implements EntityTypeBuilderExtended<T>
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @SuppressWarnings("unchecked")
    @Override
    public EntityType.Builder<T> requiredGameContext(Predicate<GameContext> predicate)
    {
        DisableApi$requiredGameContext = predicate;
        return (EntityType.Builder<T>) (Object) this;
    }

    @Override
    public Predicate<GameContext> getRequiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }

    @Inject(
            method = "build",
            at = @At("RETURN")
    )
    private void DisableApi$build(String key, CallbackInfoReturnable<EntityType<T>> cir)
    {
        EntityTypeExtended.of(cir.getReturnValue()).setRequiredGameContext(DisableApi$requiredGameContext);
    }
}
