package me.katie.curium.impl.asm;

import me.katie.curium.impl.CuriumConstants;
import me.katie.curium.impl.CuriumProperties;
import me.katie.curium.impl.asm.handlers.*;
import me.katie.curium.impl.util.Util;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.CheckClassAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Because sometimes Mixins just aren't enough...
 */
public final class CuriumMixinPlugin implements IMixinConfigPlugin {
    public static final Logger LOGGER = LoggerFactory.getLogger("Curium/ASM");
    static final List<Transformer> transformers = Util.make(new ArrayList<>(), l -> {
        l.add(new EraseHandler());
        l.add(new StubClassHandler());
        l.add(new OverwriteCtorHandler());
        l.add(new CustomTransformerHandler());
    });

    @Override
    public void onLoad(String mixinPackage) {
        // Define stubbed LWJGL callback interfaces.
        KnotLoaderHacks.defineReplacerClasses(
                "org/lwjgl/glfw",
                CuriumConstants.PACKAGE + "/stub/lwjgl",
                new String[] {
                        "GLFWErrorCallbackI"
                }
        );
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        ClassNode mixinClass = mixinInfo.getClassNode(0);

        for (Transformer transformer: transformers) {
            transformer.preMixinTransform(targetClass, mixinClass);
        }

        if (CuriumProperties.ASM_CHECK) {
            CheckClassAdapter cca = new CheckClassAdapter(null, false);
            targetClass.accept(cca);
        }

        LOGGER.debug("Applied pre-mixin transformers to {}", targetClassName);
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        ClassNode mixinClass = mixinInfo.getClassNode(0);

        for (Transformer transformer: transformers) {
            transformer.postMixinTransform(targetClass, mixinClass);
        }

        if (CuriumProperties.ASM_CHECK) {
            CheckClassAdapter cca = new CheckClassAdapter(null, false);
            targetClass.accept(cca);
        }

        LOGGER.debug("Applied post-mixin transformers to {}", targetClassName);
    }
}
