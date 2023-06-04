package xyz.apex.forge.disableapi.mixin;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import xyz.apex.forge.disableapi.api.GameContext;
import xyz.apex.forge.disableapi.api.extensions.MenuTypeExtended;

import java.util.function.Predicate;

@Mixin(MenuType.class)
public abstract class MixinMenuType<T extends AbstractContainerMenu> implements MenuTypeExtended<T>
{
    @Unique
    private Predicate<GameContext> DisableApi$requiredGameContext = GameContext.ENABLED;

    @SuppressWarnings("unchecked")
    @Override
    public MenuType<T> setRequiredGameContext(Predicate<GameContext> requiredGameContext)
    {
        DisableApi$requiredGameContext = requiredGameContext;
        return (MenuType<T>) (Object) this;
    }

    @Override
    public Predicate<GameContext> requiredGameContext()
    {
        return DisableApi$requiredGameContext;
    }
}
