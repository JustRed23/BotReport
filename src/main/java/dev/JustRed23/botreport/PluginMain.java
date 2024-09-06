package dev.JustRed23.botreport;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import org.slf4j.Logger;

@Plugin(
        id = BuildConstants.ID,
        name = BuildConstants.NAME,
        description = BuildConstants.DESCRIPTION,
        version = BuildConstants.VERSION
)
public class PluginMain {

    @Inject
    private Logger logger;

    @DataDirectory
    private Path dataDir;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
