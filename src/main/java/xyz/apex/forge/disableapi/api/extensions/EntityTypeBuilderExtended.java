package xyz.apex.forge.disableapi.api.extensions;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.function.Predicate;

/**
 * Interface to allow modders to specify a required {@link GameContext} for {@link EntityType} objects.
 */
@ApiStatus.NonExtendable
public interface EntityTypeBuilderExtended<T extends Entity>
{
    /**
     * Sets the required {@link GameContext}.
     */
    EntityType.Builder<T> requiredGameContext(Predicate<GameContext> predicate);

    /**
     * @return The required {@link GameContext}.
     */
    Predicate<GameContext> getRequiredGameContext();

    /**
     * @return Casts the given {@link EntityType.Builder} to our extended variant.
     */
    @SuppressWarnings("unchecked")
    static <T extends Entity> EntityTypeBuilderExtended<T> of(EntityType.Builder<T> builder)
    {
        return (EntityTypeBuilderExtended<T>) builder;
    }
}
