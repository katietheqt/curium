package me.katie.curium.impl.asm;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.lang.invoke.MethodType;

/**
 * General helper methods for working with ASM.
 */
public class ASMUtil {
    private ASMUtil() {
        throw new UnsupportedOperationException("Cannot instantiate AsmHelper");
    }

    /**
     * Generates a series of instructions to throw an {@link AssertionError} with a given message.
     */
    public static InsnList generateFailedAssert(String reason) {
        StringBuilder sb = new StringBuilder("curium: assertion failed");

        if (reason != null) {
            sb.append('(');
            sb.append(reason);
            sb.append(')');
        }

        // Generate instructions to throw an AssertionError.
        InsnList insns = new InsnList();

        insns.add(new TypeInsnNode(Opcodes.NEW, "java/lang/AssertionError"));
        insns.add(new InsnNode(Opcodes.DUP));
        insns.add(new LdcInsnNode(sb.toString()));
        insns.add(new MethodInsnNode(
                Opcodes.INVOKESPECIAL,
                "java/lang/AssertionError",
                "<init>",
                "(Ljava/lang/String;)V"
        ));
        insns.add(new InsnNode(Opcodes.ATHROW));

        return insns;
    }

    /**
     * Gets an ASM {@link Type} from a JVM {@link MethodType}.
     */
    public static Type methodTypeToAsmType(MethodType type) {
        Type returnType = Type.getType(type.returnType());
        Class<?>[] parameterTypes = type.parameterArray();
        Type[] asmParameterTypes = new Type[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            asmParameterTypes[i] = Type.getType(parameterTypes[i]);
        }

        return Type.getMethodType(returnType, asmParameterTypes);
    }
}
