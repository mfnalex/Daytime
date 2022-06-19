package com.jeff_media.daytime;

import co.aikar.commands.PaperCommandManager;
import com.jeff_media.daytime.commands.MainCommand;
import de.jeff_media.jefflib.JeffLib;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Daytime extends JavaPlugin {

    @SuppressWarnings("CanBeFinal")
    @Getter private static Daytime instance;
    private WorldTimeHandler worldTimeHandler;

    {
        instance = this;
    }

    @Override
    public void onEnable() {
        JeffLib.enableNMS();
        PaperCommandManager acf = new PaperCommandManager(this);
        acf.registerCommand(new MainCommand());
        reload();
    }

    public void reload() {
        if(worldTimeHandler != null) {
            worldTimeHandler.stop();
        }
        saveDefaultConfig();
        reloadConfig();
        worldTimeHandler = new WorldTimeHandler();
        worldTimeHandler.start();
    }

}
