package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.apex.forge.disableapi.api.DisableableElement;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.ItemPropertiesExtended;

import java.util.function.Predicate;

@Mixin(Item.class)
public abstract class MixinItem implements DisableableElement
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @Override
    public Predicate<GameContext> requiredGameContext()
    {
        var self = (Item) (Object) this;

        return context -> {
            // if related block is disabled, disable the item
            if(self instanceof BlockItem blockItem && !context.isEnabled(blockItem.getBlock()))
                return false;
            // if related entity type is disabled, disable the spawn egg
            if(self instanceof SpawnEggItem spawnEggItem && !context.isEnabled(((InvokerSpawnEggItem) spawnEggItem).DisableApi$getDefaultType()))
                return false;

            return DisableApi$requiredGameContext.test(context);
        };
    }

    @Inject(
            method = "<init>",
            at = @At("TAIL")
    )
    private void DisableApi$init(Item.Properties properties, CallbackInfo ci)
    {
        DisableApi$requiredGameContext = ItemPropertiesExtended.of(properties).getRequiredGameContext();
    }
}
