package api.player.server;

import org.objectweb.asm.*;

public final class ServerPlayerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ServerPlayerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	@Override
	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		super.visitMethodInsn(opcode, owner, name, desc, itf);
		if(name.equals("<init>") && owner.equals(isObfuscated ? "ahd" : "net/minecraft/entity/player/EntityPlayer"))
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/server/ServerPlayerAPI", "create", "(Lapi/player/server/IServerPlayerAPI;)Lapi/player/server/ServerPlayerAPI;", false);
			mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "qw" : "net/minecraft/entity/player/EntityPlayerMP", "serverPlayerAPI", "Lapi/player/server/ServerPlayerAPI;");

			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.ALOAD, 1);
			mv.visitIntInsn(Opcodes.ALOAD, 2);
			mv.visitIntInsn(Opcodes.ALOAD, 3);
			mv.visitIntInsn(Opcodes.ALOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/server/ServerPlayerAPI", "beforeLocalConstructing", "(Lapi/player/server/IServerPlayerAPI;Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/WorldServer;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/server/management/ItemInWorldManager;)V", false);
		}
	}

	public void visitInsn(int opcode)
	{
		if(opcode == Opcodes.RETURN)
		{
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitIntInsn(Opcodes.ALOAD, 1);
			mv.visitIntInsn(Opcodes.ALOAD, 2);
			mv.visitIntInsn(Opcodes.ALOAD, 3);
			mv.visitIntInsn(Opcodes.ALOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/server/ServerPlayerAPI", "afterLocalConstructing", "(Lapi/player/server/IServerPlayerAPI;Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/world/WorldServer;Lcom/mojang/authlib/GameProfile;Lnet/minecraft/server/management/ItemInWorldManager;)V", false);
		}
		super.visitInsn(opcode);
	}
}
