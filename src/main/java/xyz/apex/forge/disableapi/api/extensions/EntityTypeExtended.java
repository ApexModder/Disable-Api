package xyz.apex.forge.disableapi.api.extensions;

import com.google.errorprone.annotations.DoNotCall;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.DisableableElement;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.function.Predicate;

/**
 * Interface to allow modders to specify a required {@link GameContext} for {@link EntityType} objects.
 * <p>
 * This is an internal extension, to correctly make use of this use {@link EntityTypeBuilderExtended}.
 */
@ApiStatus.NonExtendable
public interface EntityTypeExtended<T extends Entity> extends DisableableElement
{
    /**
     * Sets the required {@link GameContext}.
     * <p>
     * For internal usages only, use {@link EntityTypeBuilderExtended#requiredGameContext(Predicate)} where possible.
     */
    @DoNotCall
    @ApiStatus.Internal
    void setRequiredGameContext(Predicate<GameContext> requiredGameContext);

    /**
     * @return Casts the given {@link EntityType} to our extended variant.
     */
    @SuppressWarnings("unchecked")
    static <T extends Entity> EntityTypeExtended<T> of(EntityType<T> entityType)
    {
        return (EntityTypeExtended<T>) entityType;
    }
}
