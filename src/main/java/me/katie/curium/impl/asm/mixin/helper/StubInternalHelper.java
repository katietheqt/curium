package me.katie.curium.impl.asm.mixin.helper;

import me.katie.curium.impl.asm.util.ASMUtil;
import me.katie.curium.impl.asm.mixin.annotations.StubClass;

import java.lang.invoke.MethodType;

/**
 * Internal helper to generate throws for {@link StubClass} in throwing mode.
 */
public class StubInternalHelper {
    private static final StackWalker STACK_WALKER = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);

    private StubInternalHelper() {
        throw new UnsupportedOperationException("Cannot instantiate StubInternalHelper");
    }

    public static RuntimeException getStubbedException() {
        String calledMethod = STACK_WALKER.walk(s ->
                s.skip(2)
                        .findFirst()
                        .map(f -> {
                            MethodType type = f.getMethodType();
                            String desc = ASMUtil.methodTypeToAsmType(type).getDescriptor();

                            return String.format("%s;%s%s", f.getClassName(), f.getMethodName(), desc);
                        })
                        .orElseThrow()
        );

        return new IllegalStateException("call against a stubbed out method: " + calledMethod);
    }

    public static void stubbed() {
        throw getStubbedException();
    }
}
