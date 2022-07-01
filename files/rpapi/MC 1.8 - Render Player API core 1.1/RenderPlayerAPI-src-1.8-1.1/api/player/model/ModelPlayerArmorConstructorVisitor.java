package api.player.model;

import org.objectweb.asm.*;

public final class ModelPlayerArmorConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ModelPlayerArmorConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		if(isObfuscated && name.equals("<init>") && owner.equals("net/minecraft/client/model/ModelBiped"))
			owner = "ccl";
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		if(name.equals("<init>") && owner.equals(isObfuscated ? "ccl" : "net/minecraft/client/model/ModelBiped"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.ALOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/model/ModelPlayerAPI", "create", "(Lapi/player/model/IModelPlayerAPI;FZLjava/lang/String;)Lapi/player/model/ModelPlayerAPI;", false);
			mv.visitFieldInsn(Opcodes.PUTFIELD, "api/player/model/ModelPlayerArmor", "modelPlayerAPI", "Lapi/player/model/ModelPlayerAPI;");

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
