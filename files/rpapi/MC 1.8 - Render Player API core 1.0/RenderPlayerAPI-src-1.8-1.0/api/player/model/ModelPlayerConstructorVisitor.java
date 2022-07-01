package api.player.model;

import org.objectweb.asm.*;

public final class ModelPlayerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ModelPlayerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		if(name.equals("<init>") && owner.equals(isObfuscated ? "ccl" : "net/minecraft/client/model/ModelBiped"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "create", "(Lapi/player/model/IModelPlayerAPI;)Lapi/player/model/ModelPlayerAPI;", false);
			mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "cct" : "net/minecraft/client/model/ModelPlayer", "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;");

			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.FLOAD, 1);
			mv.visitIntInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "beforeLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FZ)V", false);
		}
	}

	public void visitInsn(int opcode)
	{
		if(opcode == Opcodes.RETURN)
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.FLOAD, 1);
			mv.visitIntInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "afterLocalConstructing", "(Lapi/player/model/IModelPlayerAPI;FZ)V", false);
		}
		super.visitInsn(opcode);
	}
}
