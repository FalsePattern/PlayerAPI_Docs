package api.player.render;

import org.objectweb.asm.*;

public final class RenderPlayerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public RenderPlayerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		if(name.equals("<init>") && owner.equals(isObfuscated ? "cqv" : "net/minecraft/client/renderer/entity/RendererLivingEntity"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "create", "(Lapi/player/render/IRenderPlayerAPI;)Lapi/player/render/RenderPlayerAPI;", false);
			mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "ctc" : "net/minecraft/client/renderer/entity/RenderPlayer", "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;");

			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.ALOAD, 1);
			mv.visitIntInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "beforeLocalConstructing", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/renderer/entity/RenderManager;Z)V", false);
		}
	}

	public void visitInsn(int opcode)
	{
		if(opcode == Opcodes.RETURN)
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.ALOAD, 1);
			mv.visitIntInsn(Opcodes.ILOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "afterLocalConstructing", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/renderer/entity/RenderManager;Z)V", false);
		}
		super.visitInsn(opcode);
	}
}
