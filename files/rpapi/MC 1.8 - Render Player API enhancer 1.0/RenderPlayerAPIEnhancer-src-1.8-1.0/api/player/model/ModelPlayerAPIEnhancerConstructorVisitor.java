package api.player.model;

import org.objectweb.asm.MethodVisitor;

public class ModelPlayerAPIEnhancerConstructorVisitor extends MethodVisitor
{
	private final boolean isObfuscated;

	public ModelPlayerAPIEnhancerConstructorVisitor(MethodVisitor paramMethodVisitor, boolean isObfuscated)
	{
		super(262144, paramMethodVisitor);
		this.isObfuscated = isObfuscated;
	}

	public void visitMethodInsn(int opcode, String owner, String name, String desc, boolean itf)
	{
		if(name.equals("<init>") && ((isObfuscated && owner.equals("ccl")) || owner.equals("net/minecraft/client/model/ModelBiped")))
			owner = owner.equals("ccl") ? "api/player/model/ModelPlayerArmor" : "api/player/model/ModelPlayerArmor";
		super.visitMethodInsn(opcode, owner, name, desc, itf);
	}
}
