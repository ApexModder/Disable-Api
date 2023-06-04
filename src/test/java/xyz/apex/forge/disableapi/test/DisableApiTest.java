package xyz.apex.forge.disableapi.test;

import com.google.errorprone.annotations.DoNotCall;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.ApiStatus;
import xyz.apex.forge.disableapi.DisableApi;
import xyz.apex.forge.disableapi.api.extensions.ItemPropertiesExtended;

@ApiStatus.Internal
public final class DisableApiTest
{
    @DoNotCall
    public static void enable(DisableApi dummy) // dummy object to ensure only we can invoke this method
    {
        // validate not null to ensure call came from our constructor
        // not the best solution, but it works
        Validate.notNull(dummy);
        // validate that our mod is active context
        Validate.isTrue(ModLoadingContext.get().getActiveNamespace().equals(DisableApi.ID));

        // create our registries
        var blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, DisableApi.ID);
        var items = DeferredRegister.create(ForgeRegistries.ITEMS, DisableApi.ID);

        // register new generic stone like block
        var testBlock = blocks.register(
                "test_block", // block name
                () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)) // with same properties as stone
        );

        // register new generic block item used to place our new block in level
        items.register(
                "test_block", // item name
                () -> new BlockItem(
                        testBlock.get(), // pass our block
                        ItemPropertiesExtended.of(new Item.Properties()) // `new Item.Properties()` this items properties, blank dummy object, no custom properties here
                                              .requiredGameContext(context -> context.isModLoaded("jei")) // mark this item as only being enabled if JEI is present
                )
        );

        // register our registries
        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        blocks.register(modBus);
        items.register(modBus);
    }
}
