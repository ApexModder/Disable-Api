package xyz.apex.forge.disableapi.api;

import net.minecraft.core.RegistryAccess;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.impl.GameContextImpl;

import java.util.Set;
import java.util.function.Predicate;

/**
 * Interface denoting a context of the current game state.
 */
@ApiStatus.NonExtendable
public interface GameContext
{
    /**
     * Context stating element should be enabled.
     */
    Predicate<GameContext> ENABLED = context -> true;

    /**
     * Context stating element should be disabled.
     */
    Predicate<GameContext> DISABLED = context -> false;

    /**
     * @return {@link RegistryAccess} instance, used to do various registry related lookups.
     */
    RegistryAccess registryAccess();

    /**
     * @return The currently enabled feature flags.
     */
    FeatureFlagSet enabledFeatures();

    /**
     * @return True if given feature is enabled.
     */
    boolean isFeatureEnabled(FeatureFlag feature);

    /**
     * @return True if given features are enabled.
     */
    boolean hasFeatures(FeatureFlagSet features);

    /**
     * @return Immutable set containing all installed mods.
     */
    Set<String> mods();

    /**
     * @return True if given mod id is installed.
     */
    boolean isModLoaded(String modId);

    /**
     * @return True if given element is enabled.
     */
    boolean isEnabled(DisableableElement element);

    /**
     * @return True if given element is enabled.
     */
    boolean isEnabled(FeatureElement element);

    /**
     * @return Global instance.
     */
    static GameContext get()
    {
        return GameContextImpl.INSTANCE;
    }
}
