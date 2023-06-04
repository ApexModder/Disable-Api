package xyz.apex.forge.disableapi;

import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;

import java.lang.reflect.InvocationTargetException;

@Mod(DisableApi.ID)
public final class DisableApi
{
    public static final String ID = "disableapi";

    public DisableApi()
    {
        try
        {
            var clazz = Class.forName("xyz.apex.forge.disableapi.test.DisableApiTest");
            var method = clazz.getMethod("enable", DisableApi.class);
            method.setAccessible(true);
            method.invoke(null, this);
        }
        catch(ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e)
        {
            LogManager.getLogger().debug("Could not find DisableApiTest class, Test elements will be disabled.");
        }
    }
}