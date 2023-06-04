package xyz.apex.forge.disableapi.impl;

import com.google.errorprone.annotations.DoNotCall;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.flag.FeatureElement;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModInfo;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.DisableableElement;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.Collections;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ApiStatus.Internal
public final class GameContextImpl implements GameContext
{
    public static final GameContextImpl INSTANCE = new GameContextImpl();

    private Supplier<FeatureFlagSet> enabledFeatures = () -> FeatureFlags.DEFAULT_FLAGS;
    private Supplier<RegistryAccess> registryAccess = () -> RegistryAccess.EMPTY;
    private Set<String> mods = Collections.emptySet();

    private GameContextImpl()
    {
    }

    @Override
    public RegistryAccess registryAccess()
    {
        return registryAccess.get();
    }

    @Override
    public FeatureFlagSet enabledFeatures()
    {
        return enabledFeatures.get();
    }

    @Override
    public boolean isFeatureEnabled(FeatureFlag feature)
    {
        return enabledFeatures().contains(feature);
    }

    @Override
    public boolean hasFeatures(FeatureFlagSet features)
    {
        return enabledFeatures().isSubsetOf(features);
    }

    @Override
    public Set<String> mods()
    {
        return mods;
    }

    @Override
    public boolean isModLoaded(String modId)
    {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isEnabled(DisableableElement element)
    {
        return hasFeatures(element.requiredFeatures()) && element.requiredGameContext().test(this);
    }

    @Override
    public boolean isEnabled(FeatureElement element)
    {
        if(element instanceof DisableableElement disableable)
            return isEnabled(disableable);
        return hasFeatures(element.requiredFeatures());
    }

    @DoNotCall
    @ApiStatus.Internal
    public void reload(Supplier<FeatureFlagSet> enabledFeatures, Supplier<RegistryAccess> registryAccess)
    {
        this.enabledFeatures = enabledFeatures;
        this.registryAccess = registryAccess;

        mods = ModList.get().getMods().stream().map(IModInfo::getModId).collect(Collectors.toSet());
    }
}
