package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.ItemPropertiesExtended;

import java.util.function.Predicate;

@Mixin(Item.Properties.class)
public class MixinItemProperties implements ItemPropertiesExtended
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @Override
    public Item.Properties requiredGameContext(Predicate<GameContext> predicate)
    {
        DisableApi$requiredGameContext = predicate;
        return (Item.Properties) (Object) this;
    }

    @Override
    public Predicate<GameContext> getRequiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }
}
