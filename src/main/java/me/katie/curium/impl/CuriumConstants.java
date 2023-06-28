package me.katie.curium.impl;

import com.google.common.collect.ImmutableList;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Constants for Curium.
 */
public class CuriumConstants {

    private CuriumConstants() {
        throw new UnsupportedOperationException("Cannot instantiate CuriumConstants");
    }

    public static final Logger LOGGER = LoggerFactory.getLogger("Curium");

    /**
     * Sections of the "system report" portion of a crash report that will be skipped.
     */
    public static final List<String> OMITTED_SYSTEM_REPORT_SECTIONS = ImmutableList.of(
            /* "Backend library", */ "Window size", "GL Caps", "GL debug messages", "Using VBOs", "GPU Warnings",
            "Backend API", "Graphics mode"
    );

    public static final String PACKAGE = CuriumConstants.class.getPackageName().replace('.', '/');
    public static final String DESCRIPTION;
    public static final String USING_CURIUM = "<using curium>";

    static {
        ModContainer container = FabricLoader.getInstance().getModContainer("curium").orElseThrow();
        ModMetadata metadata = container.getMetadata();

        DESCRIPTION = "<replaced by " + metadata.getName() + " " + metadata.getVersion().getFriendlyString() + ">";
    }
}
