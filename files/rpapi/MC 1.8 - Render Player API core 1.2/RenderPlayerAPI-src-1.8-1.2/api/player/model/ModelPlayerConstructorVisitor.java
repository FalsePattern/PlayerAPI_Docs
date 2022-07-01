package api.player.model;

import org.objectweb.asm.*;

public final class ModelPlayerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;
	private final int parameterOffset;

	public ModelPlayerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated, int parameterOffset)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
		this.parameterOffset = parameterOffset;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		if(name.equals("<init>") && owner.equals(isObfuscated ? "obf_patterns/playerapi_1_7_2/ModelPlayer" : "deobf_patterns/playerapi_1_7_2/ModelPlayer"))
		{
			desc = desc.substring(0, desc.indexOf(")")) + "Ljava/lang/String;)V";
			mv.visitVarInsn(Opcodes.ALOAD, parameterOffset);
		}
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		if(name.equals("<init>") && owner.equals(isObfuscated ? "ccl" : "net/minecraft/client/model/ModelBiped"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitLdcInsn(0F);
			mv.visitLdcInsn(64);
			mv.visitLdcInsn(64);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.ALOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "create", "(Lapi/player/model/IModelPlayerAPI;FFIIZLjava/lang/String;)Lapi/player/model/ModelPlayerAPI;", false);
			mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "cct" : "net/minecraft/client/model/ModelPlayer", "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;");

			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitLdcInsn(0F);
			mv.visitLdcInsn(64);
			mv.visitLdcInsn(64);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "beforeLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FFIIZ)V", false);
		}
	}

	public void visitInsn(int opcode)
	{
		if(opcode == Opcodes.RETURN)
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitLdcInsn(0F);
			mv.visitLdcInsn(64);
			mv.visitLdcInsn(64);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "afterLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FFIIZ)V", false);
		}
		super.visitInsn(opcode);
	}
}
