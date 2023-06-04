package xyz.apex.forge.disableapi.api;

import com.google.errorprone.annotations.DoNotCall;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Predicate;

/**
 * Extended version of {@link FeatureElement}.
 * <p>
 * Allows mods to specify a specific {@link GameContext} to be required for this element.
 * <p>
 * All default implementations of {@link FeatureElement} are redirected to use this system.
 * If you have any custom implementations, it is recommended to redirect to this system if possible.
 */
@ApiStatus.NonExtendable
public interface DisableableElement extends FeatureElement
{
    /**
     * @return The required game state for this element.
     */
    @DoNotCall
    Predicate<GameContext> requiredGameContext();

    /**
     * Redirect the vanilla implementation to use our more advanced system.
     */
    @Deprecated
    @Override
    default boolean isEnabled(FeatureFlagSet enabledFeatures)
    {
        return GameContext.get().isEnabled(this);
    }

    /**
     * @return Converts the given element to our variant.
     */
    static DisableableElement of(FeatureElement element)
    {
        return (DisableableElement) element;
    }
}
