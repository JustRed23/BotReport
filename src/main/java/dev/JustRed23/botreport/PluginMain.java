package dev.JustRed23.botreport;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.JustRed23.botreport.config.CaseConverter;
import dev.JustRed23.botreport.config.Config;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.ConstructorException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Plugin(
        id = BuildConstants.ID,
        name = BuildConstants.NAME,
        description = BuildConstants.DESCRIPTION,
        version = BuildConstants.VERSION
)
public class PluginMain {

    private final ProxyServer server;
    private final Logger logger;
    private final Path dataDir;

    private static Config config;

    @Inject
    public PluginMain(ProxyServer server, Logger logger, @DataDirectory Path dataDir) {
        this.server = server;
        this.logger = logger;
        this.dataDir = dataDir;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        boolean loaded = false;
        logger.info("Loading config...");
        try {
            loadConfig();
            loaded = true;
        } catch (ConstructorException ce) {
            logger.error("Invalid configuration detected", ce);
        } catch (Exception e) {
            logger.error("Failed to load config", e);
        }

        if (!loaded) {
            logger.error("{} failed to load, please check the logs for more information", BuildConstants.NAME);
            return;
        }

        logger.info("{} has been loaded successfully!", BuildConstants.NAME);
    }

    private void loadConfig() throws IOException {
        if (!Files.exists(dataDir)) {
            logger.warn("Data directory does not exist, creating...");
            Files.createDirectories(dataDir);
        }

        Path configFile = dataDir.resolve("config.yml");

        if (!Files.exists(configFile)) {
            logger.warn("Config file does not exist, copying default...");
            Files.copy(getClass().getResourceAsStream("/config.yml"), configFile);
        }

        Constructor constructor = new Constructor(Config.class);
        constructor.setAllowDuplicateKeys(false);
        constructor.setPropertyUtils(new CaseConverter()); //convert hyphenated keys to camelCase to keep Java naming conventions

        Yaml yaml = new Yaml(constructor);
        config = yaml.load(Files.newBufferedReader(configFile));
    }

    public static Config getConfig() {
        return config;
    }
}
