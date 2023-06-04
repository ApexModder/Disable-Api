package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.SpawnEggItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnEggItem.class)
public interface InvokerSpawnEggItem
{
    @Invoker("getDefaultType")
    EntityType<?> DisableApi$getDefaultType();
}
