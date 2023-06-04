package xyz.apex.forge.disableapi.api.extensions;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.function.Predicate;

/**
 * Interface to allow modders to specify a required {@link GameContext} for {@link Block} objects.
 */
@ApiStatus.NonExtendable
public interface BlockPropertiesExtended
{
    /**
     * Sets the required {@link GameContext}.
     */
    BlockBehaviour.Properties requiredGameContext(Predicate<GameContext> predicate);

    /**
     * @return The required {@link GameContext}.
     */
    Predicate<GameContext> getRequiredGameContext();

    /**
     * @return Casts the given {@link BlockBehaviour.Properties} to our extended variant.
     */
    static BlockPropertiesExtended of(BlockBehaviour.Properties properties)
    {
        return (BlockPropertiesExtended) properties;
    }
}
