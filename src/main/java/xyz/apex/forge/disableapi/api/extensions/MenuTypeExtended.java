package xyz.apex.forge.disableapi.api.extensions;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.api.DisableableElement;
import xyz.apex.forge.disableapi.api.GameContext;

import java.util.function.Predicate;

/**
 * Interface to allow modders to specify a required {@link GameContext} for {@link MenuType} objects.
 */
@ApiStatus.NonExtendable
public interface MenuTypeExtended<T extends AbstractContainerMenu> extends DisableableElement
{
    /**
     * Sets the required {@link GameContext}.
     */
    MenuType<T> setRequiredGameContext(Predicate<GameContext> requiredGameContext);

    /**
     * @return Casts the given {@link MenuType} to our extended variant.
     */
    @SuppressWarnings("unchecked")
    static <T extends AbstractContainerMenu> MenuTypeExtended<T> of(MenuType<T> menuType)
    {
        return (MenuTypeExtended<T>) menuType;
    }
}
