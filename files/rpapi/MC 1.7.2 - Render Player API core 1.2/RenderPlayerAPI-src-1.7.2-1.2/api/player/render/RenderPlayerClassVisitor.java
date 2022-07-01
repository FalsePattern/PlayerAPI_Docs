package api.player.render;

import java.io.*;
import java.util.*;

import org.objectweb.asm.*;

public final class RenderPlayerClassVisitor extends ClassVisitor
{
	public static final String targetClassName = "net.minecraft.client.renderer.entity.RenderPlayer";

	private boolean hadLocalDoRenderLabel;
	private boolean hadLocalDoRenderShadowAndFire;
	private boolean hadLocalGetColorMultiplier;
	private boolean hadLocalGetDeathMaxRotation;
	private boolean hadLocalGetFontRendererFromRenderManager;
	private boolean hadLocalGetResourceLocationFromPlayer;
	private boolean hadLocalHandleRotationFloat;
	private boolean hadLocalInheritRenderPass;
	private boolean hadLocalLoadTexture;
	private boolean hadLocalLoadTextureOfEntity;
	private boolean hadLocalPassSpecialRender;
	private boolean hadLocalPerformStaticEntityRebuild;
	private boolean hadLocalRenderArrowsStuckInEntity;
	private boolean hadLocalRenderFirstPersonArm;
	private boolean hadLocalRenderLivingLabel;
	private boolean hadLocalRenderModel;
	private boolean hadLocalRenderPlayer;
	private boolean hadLocalRenderPlayerNameAndScoreLabel;
	private boolean hadLocalRenderPlayerScale;
	private boolean hadLocalRenderPlayerSleep;
	private boolean hadLocalRenderSpecials;
	private boolean hadLocalRenderSwingProgress;
	private boolean hadLocalRotatePlayer;
	private boolean hadLocalSetArmorModel;
	private boolean hadLocalSetPassArmorModel;
	private boolean hadLocalSetRenderManager;
	private boolean hadLocalSetRenderPassModel;
	private boolean hadLocalUpdateIcons;

	public static byte[] transform(byte[] bytes, boolean isObfuscated, Map<String, Stack<String>> constructorReplacements)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			RenderPlayerClassVisitor p = new RenderPlayerClassVisitor(cw, isObfuscated, constructorReplacements);

			cr.accept(p, 0);

			byte[] result = cw.toByteArray();
			in.close();
			return result;
		}
		catch(IOException ioe)
		{
			throw new RuntimeException(ioe);
		}
	}

	private final boolean isObfuscated;
	private final Map<String, Stack<String>> constructorReplacements;

	public RenderPlayerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated, Map<String, Stack<String>> constructorReplacements)
	{
		super(262144, classVisitor);
		this.isObfuscated = isObfuscated;
		this.constructorReplacements = constructorReplacements;
	}

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		String[] newInterfaces = new String[interfaces.length + 1];
		for(int i=0; i<interfaces.length; i++)
			newInterfaces[i] = interfaces[i];
		newInterfaces[interfaces.length] = "api/player/render/IRenderPlayerAPI";
		super.visit(version, access, name, signature, superName, newInterfaces);
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if(name.equals("<init>"))
			return new RenderPlayerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated, constructorReplacements);

		if(name.equals(isObfuscated ? "b" : "func_110813_b") && desc.equals(isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z"))
		{
			hadLocalDoRenderLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "doRenderShadowAndFire") && desc.equals(isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V"))
		{
			hadLocalDoRenderShadowAndFire = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getColorMultiplier") && desc.equals(isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I"))
		{
			hadLocalGetColorMultiplier = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getDeathMaxRotation") && desc.equals(isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F"))
		{
			hadLocalGetDeathMaxRotation = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "getFontRendererFromRenderManager") && desc.equals(isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;"))
		{
			hadLocalGetFontRendererFromRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getEntityTexture") && desc.equals(isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;"))
		{
			hadLocalGetResourceLocationFromPlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetResourceLocationFromPlayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "handleRotationFloat") && desc.equals(isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F"))
		{
			hadLocalHandleRotationFloat = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "inheritRenderPass") && desc.equals(isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I"))
		{
			hadLocalInheritRenderPass = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInheritRenderPass", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "bindTexture") && desc.equals(isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V"))
		{
			hadLocalLoadTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "bindEntityTexture") && desc.equals(isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V"))
		{
			hadLocalLoadTextureOfEntity = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTextureOfEntity", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "passSpecialRender") && desc.equals(isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V"))
		{
			hadLocalPassSpecialRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPassSpecialRender", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "func_147905_a") && desc.equals("()Z"))
		{
			hadLocalPerformStaticEntityRebuild = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPerformStaticEntityRebuild", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "e" : "renderArrowsStuckInEntity") && desc.equals(isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V"))
		{
			hadLocalRenderArrowsStuckInEntity = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderArrowsStuckInEntity", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderFirstPersonArm") && desc.equals(isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V"))
		{
			hadLocalRenderFirstPersonArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderFirstPersonArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "func_147906_a") && desc.equals(isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V"))
		{
			hadLocalRenderLivingLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderModel") && desc.equals(isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V"))
		{
			hadLocalRenderModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "doRender") && desc.equals(isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V"))
		{
			hadLocalRenderPlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "func_96449_a") && desc.equals(isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V"))
		{
			hadLocalRenderPlayerNameAndScoreLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerNameAndScoreLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "preRenderCallback") && desc.equals(isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"))
		{
			hadLocalRenderPlayerScale = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerScale", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLivingAt") && desc.equals(isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V"))
		{
			hadLocalRenderPlayerSleep = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerSleep", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderEquippedItems") && desc.equals(isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"))
		{
			hadLocalRenderSpecials = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSpecials", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "renderSwingProgress") && desc.equals(isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F"))
		{
			hadLocalRenderSwingProgress = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSwingProgress", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "rotateCorpse") && desc.equals(isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V"))
		{
			hadLocalRotatePlayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotatePlayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "shouldRenderPass") && desc.equals(isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I"))
		{
			hadLocalSetArmorModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetArmorModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "func_82408_c") && desc.equals(isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V"))
		{
			hadLocalSetPassArmorModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPassArmorModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setRenderManager") && desc.equals(isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V"))
		{
			hadLocalSetRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderManager", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setRenderPassModel") && desc.equals(isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V"))
		{
			hadLocalSetRenderPassModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderPassModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "updateIcons") && desc.equals(isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V"))
		{
			hadLocalUpdateIcons = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateIcons", desc, signature, exceptions);
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	public void visitEnd()
	{
		MethodVisitor mv;

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "func_110813_b", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRenderLabel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;" : "Lnet/minecraft/entity/EntityLivingBase;") + ")Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRenderLabel", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "func_110813_b", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRenderLabel", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "func_110813_b", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalDoRenderLabel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderLabel", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "func_110813_b", "" + (isObfuscated ? "(Lrh;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRenderShadowAndFire", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lqn;DDDFF" : "Lnet/minecraft/entity/Entity;DDDFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalDoRenderShadowAndFire)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lqn;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getColorMultiplier", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;FF" : "Lnet/minecraft/entity/EntityLivingBase;FF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetColorMultiplier)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lrh;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getDeathMaxRotation", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;" : "Lnet/minecraft/entity/EntityLivingBase;") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetDeathMaxRotation)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lrh;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "");
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getFontRendererFromRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbag;" : "Lnet/minecraft/client/gui/FontRenderer;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetFontRendererFromRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbag;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "");
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getResourceLocationFromPlayer", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;" : "Lnet/minecraft/client/entity/AbstractClientPlayer;") + ")" + (isObfuscated ? "Lbqo;" : "Lnet/minecraft/util/ResourceLocation;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetResourceLocationFromPlayer", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetResourceLocationFromPlayer", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetResourceLocationFromPlayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetResourceLocationFromPlayer", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbkz;)Lbqo;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "");
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "handleRotationFloat", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realHandleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superHandleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalHandleRotationFloat)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "inheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "inheritRenderPass", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;IF" : "Lnet/minecraft/entity/EntityLivingBase;IF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realInheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "inheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superInheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "inheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalInheritRenderPass)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "inheritRenderPass", "" + (isObfuscated ? "(Lrh;IF)I" : "(Lnet/minecraft/entity/EntityLivingBase;IF)I") + "");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "loadTexture", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbqo;" : "Lnet/minecraft/util/ResourceLocation;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realLoadTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superLoadTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalLoadTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lbqo;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "bindEntityTexture", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "loadTextureOfEntity", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lqn;" : "Lnet/minecraft/entity/Entity;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realLoadTextureOfEntity", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "bindEntityTexture", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superLoadTextureOfEntity", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "bindEntityTexture", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalLoadTextureOfEntity)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localLoadTextureOfEntity", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "bindEntityTexture", "" + (isObfuscated ? "(Lqn;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "passSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "passSpecialRender", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;DDD" : "Lnet/minecraft/entity/EntityLivingBase;DDD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPassSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "passSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPassSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "passSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalPassSpecialRender)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPassSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "passSpecialRender", "" + (isObfuscated ? "(Lrh;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "func_147905_a", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "performStaticEntityRebuild", "(Lapi/player/render/IRenderPlayerAPI;)Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPerformStaticEntityRebuild", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "func_147905_a", "()Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPerformStaticEntityRebuild", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_147905_a", "()Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalPerformStaticEntityRebuild)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPerformStaticEntityRebuild", "()Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_147905_a", "()Z");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "e" : "renderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderArrowsStuckInEntity", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "renderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "e" : "renderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderArrowsStuckInEntity)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "e" : "renderArrowsStuckInEntity", "" + (isObfuscated ? "(Lrh;F)V" : "(Lnet/minecraft/entity/EntityLivingBase;F)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderFirstPersonArm", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lxl;" : "Lnet/minecraft/entity/player/EntityPlayer;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderFirstPersonArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderFirstPersonArm", "" + (isObfuscated ? "(Lxl;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "func_147906_a", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLivingLabel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lqn;Ljava/lang/String;DDDI" : "Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLivingLabel", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "func_147906_a", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLivingLabel", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_147906_a", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLivingLabel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 3);
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			mv.visitVarInsn(Opcodes.DLOAD, 7);
			mv.visitVarInsn(Opcodes.ILOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_147906_a", "" + (isObfuscated ? "(Lqn;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderModel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;FFFFFF" : "Lnet/minecraft/entity/EntityLivingBase;FFFFFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lrh;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayer", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;DDDFF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayer", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayer", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderPlayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayer", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbkz;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "func_96449_a", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerNameAndScoreLabel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;DDDLjava/lang/String;FD" : "Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerNameAndScoreLabel", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "func_96449_a", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerNameAndScoreLabel", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_96449_a", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderPlayerNameAndScoreLabel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerNameAndScoreLabel", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.ALOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitVarInsn(Opcodes.DLOAD, 10);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "func_96449_a", "" + (isObfuscated ? "(Lbkz;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "preRenderCallback", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerScale", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;F" : "Lnet/minecraft/client/entity/AbstractClientPlayer;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerScale", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "preRenderCallback", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerScale", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "preRenderCallback", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderPlayerScale)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerScale", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "preRenderCallback", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderPlayerSleep", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;DDD" : "Lnet/minecraft/client/entity/AbstractClientPlayer;DDD") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderPlayerSleep", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderPlayerSleep", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderPlayerSleep)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderPlayerSleep", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbkz;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderEquippedItems", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderSpecials", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;F" : "Lnet/minecraft/client/entity/AbstractClientPlayer;F") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderSpecials", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderEquippedItems", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderSpecials", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderEquippedItems", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderSpecials)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSpecials", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderEquippedItems", "" + (isObfuscated ? "(Lbkz;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "renderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderSwingProgress", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lrh;F" : "Lnet/minecraft/entity/EntityLivingBase;F") + ")F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "renderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "renderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderSwingProgress)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "renderSwingProgress", "" + (isObfuscated ? "(Lrh;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "");
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "rotatePlayer", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;FFF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;FFF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRotatePlayer", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRotatePlayer", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRotatePlayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotatePlayer", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbkz;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "shouldRenderPass", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setArmorModel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;IF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;IF") + ")I");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetArmorModel", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "shouldRenderPass", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetArmorModel", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "shouldRenderPass", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetArmorModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetArmorModel", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "shouldRenderPass", "" + (isObfuscated ? "(Lbkz;IF)I" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)I") + "");
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "func_82408_c", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setPassArmorModel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbkz;IF" : "Lnet/minecraft/client/entity/AbstractClientPlayer;IF") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetPassArmorModel", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "func_82408_c", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetPassArmorModel", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ILOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "func_82408_c", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetPassArmorModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPassArmorModel", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ILOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "func_82408_c", "" + (isObfuscated ? "(Lbkz;IF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;IF)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setRenderManager", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbnf;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "setRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderManager", "" + (isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setRenderPassModel", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lbhl;" : "Lnet/minecraft/client/model/ModelBase;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "setRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetRenderPassModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderPassModel", "" + (isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "updateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "updateIcons", "(Lapi/player/render/IRenderPlayerAPI;" + (isObfuscated ? "Lpt;" : "Lnet/minecraft/client/renderer/texture/IIconRegister;") + ")V");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUpdateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "updateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUpdateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "updateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalUpdateIcons)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bnz" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "updateIcons", "" + (isObfuscated ? "(Lpt;)V" : "(Lnet/minecraft/client/renderer/texture/IIconRegister;)V") + "");
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMainModelField", isObfuscated ? "()Lbhl;" : "()Lnet/minecraft/client/model/ModelBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "i" : "mainModel", isObfuscated ? "Lbhl;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMainModelField", isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "i" : "mainModel", isObfuscated ? "Lbhl;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelArmorField", isObfuscated ? "()Lbhg;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "h" : "modelArmor", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelArmorField", isObfuscated ? "(Lbhg;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "h" : "modelArmor", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelArmorChestplateField", isObfuscated ? "()Lbhg;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "g" : "modelArmorChestplate", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelArmorChestplateField", isObfuscated ? "(Lbhg;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "g" : "modelArmorChestplate", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getModelBipedMainField", isObfuscated ? "()Lbhg;" : "()Lnet/minecraft/client/model/ModelBiped;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "modelBipedMain", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setModelBipedMainField", isObfuscated ? "(Lbhg;)V" : "(Lnet/minecraft/client/model/ModelBiped;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "modelBipedMain", isObfuscated ? "Lbhg;" : "Lnet/minecraft/client/model/ModelBiped;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderBlocksField", isObfuscated ? "()Lble;" : "()Lnet/minecraft/client/renderer/RenderBlocks;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "field_147909_c", isObfuscated ? "Lble;" : "Lnet/minecraft/client/renderer/RenderBlocks;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderBlocksField", isObfuscated ? "(Lble;)V" : "(Lnet/minecraft/client/renderer/RenderBlocks;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "field_147909_c", isObfuscated ? "Lble;" : "Lnet/minecraft/client/renderer/RenderBlocks;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderManagerField", isObfuscated ? "()Lbnf;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderManager", isObfuscated ? "Lbnf;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderManagerField", isObfuscated ? "(Lbnf;)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderManager", isObfuscated ? "Lbnf;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPassModelField", isObfuscated ? "()Lbhl;" : "()Lnet/minecraft/client/model/ModelBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "j" : "renderPassModel", isObfuscated ? "Lbhl;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderPassModelField", isObfuscated ? "(Lbhl;)V" : "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "j" : "renderPassModel", isObfuscated ? "Lbhl;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowOpaqueField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowOpaqueField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowSizeField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowSizeField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSteveTexturesField", isObfuscated ? "()Lbqo;" : "()Lnet/minecraft/util/ResourceLocation;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "steveTextures", isObfuscated ? "Lbqo;" : "Lnet/minecraft/util/ResourceLocation;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBase", "(Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBase", "(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBaseIds", "()Ljava/util/Set;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBaseIds", "(Lapi/player/render/IRenderPlayerAPI;)Ljava/util/Set;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "dynamic", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "dynamic", "(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerAPI", "()Lapi/player/render/RenderPlayerAPI;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "boh" : "net/minecraft/client/renderer/entity/RenderPlayer", "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayer", isObfuscated ? "()Lboh;" : "()Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getAllInstances", isObfuscated ? "()[Lboh;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getAllInstances", isObfuscated ? "()[Lboh;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		cv.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;", null, null);
	}
}
