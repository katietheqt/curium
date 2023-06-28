package me.katie.curium.impl.asm.mixin;

import me.katie.curium.impl.CuriumProperties;
import me.katie.curium.impl.asm.CuriumASM;
import me.katie.curium.impl.asm.mixin.transformers.CustomTransformerHandler;
import me.katie.curium.impl.asm.mixin.transformers.EraseHandler;
import me.katie.curium.impl.asm.mixin.transformers.OverwriteCtorHandler;
import me.katie.curium.impl.asm.mixin.transformers.StubClassHandler;
import me.katie.curium.impl.util.Util;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.util.CheckClassAdapter;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Bootstrapper for {@link CuriumASM} and class transformer applicator.
 */
public final class CuriumMixinPlugin implements IMixinConfigPlugin {
    private static final List<ClassTransformer> TRANSFORMERS = Util.make(new ArrayList<>(), l -> {
        l.add(new EraseHandler());
        l.add(new StubClassHandler());
        l.add(new OverwriteCtorHandler());
        l.add(new CustomTransformerHandler());
    });

    @Override
    public void onLoad(String mixinPackage) {
        try {
            CuriumASM.init();
        } catch (Throwable e) {
            CuriumASM.LOGGER.error("CuriumASM initalization failed", e);
        }
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

        for (ClassTransformer transformer: TRANSFORMERS) {
            transformer.preMixinTransform(targetClass, mixinClass);
        }

        if (CuriumProperties.ASM_CHECK) {
            CheckClassAdapter cca = new CheckClassAdapter(null, false);
            targetClass.accept(cca);
        }

        CuriumASM.LOGGER.debug("Applied pre-mixin transformers to {}", targetClassName);
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        ClassNode mixinClass = mixinInfo.getClassNode(0);

        for (ClassTransformer transformer: TRANSFORMERS) {
            transformer.postMixinTransform(targetClass, mixinClass);
        }

        if (CuriumProperties.ASM_CHECK) {
            CheckClassAdapter cca = new CheckClassAdapter(null, false);
            targetClass.accept(cca);
        }

        CuriumASM.LOGGER.debug("Applied post-mixin transformers to {}", targetClassName);
    }
}
