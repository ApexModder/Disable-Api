package xyz.apex.forge.disableapi.api.extensions;

import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.function.Predicate;

/**
 * Interface to allow modders to specify a required {@link GameContext} for {@link Item} objects.
 */
@ApiStatus.NonExtendable
public interface ItemPropertiesExtended
{
    /**
     * Sets the required {@link GameContext}.
     */
    Item.Properties requiredGameContext(Predicate<GameContext> predicate);

    /**
     * @return The required {@link GameContext}.
     */
    Predicate<GameContext> getRequiredGameContext();

    /**
     * @return Casts the given {@link Item.Properties} to our extended variant.
     */
    static ItemPropertiesExtended of(Item.Properties properties)
    {
        return (ItemPropertiesExtended) properties;
    }
}
