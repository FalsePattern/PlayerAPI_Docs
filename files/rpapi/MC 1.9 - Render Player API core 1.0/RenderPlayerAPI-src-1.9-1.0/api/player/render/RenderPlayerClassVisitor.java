// ==================================================================
// This file is part of Render Player API.
//
// Render Player API is free software: you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public License
// as published by the Free Software Foundation, either version 3 of
// the License, or (at your option) any later version.
//
// Render Player API is distributed in the hope that it will be
// useful, but WITHOUT ANY WARRANTY; without even the implied
// warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License and the GNU General Public License along with Render
// Player API. If not, see <http://www.gnu.org/licenses/>.
// ==================================================================

package api.player.render;

import java.io.*;
import java.util.*;

import org.objectweb.asm.*;

public final class RenderPlayerClassVisitor extends ClassVisitor
{
	public static final String targetClassName = "net.minecraft.client.renderer.entity.RenderPlayer";

	private boolean hadLocalBindEntityTexture;
	private boolean hadLocalBindTexture;
	private boolean hadLocalCanRenderName;
	private boolean hadLocalDoRender;
	private boolean hadLocalDoRenderShadowAndFire;
	private boolean hadLocalGetEntityTexture;
	private boolean hadLocalGetFontRendererFromRenderManager;
	private boolean hadLocalGetMainModel;
	private boolean hadLocalGetRenderManager;
	private boolean hadLocalGetTeamColor;
	private boolean hadLocalIsMultipass;
	private boolean hadLocalPreRenderCallback;
	private boolean hadLocalRenderEntityName;
	private boolean hadLocalRenderLeftArm;
	private boolean hadLocalRenderLivingAt;
	private boolean hadLocalRenderLivingLabel;
	private boolean hadLocalRenderMultipass;
	private boolean hadLocalRenderName;
	private boolean hadLocalRenderRightArm;
	private boolean hadLocalRotateCorpse;
	private boolean hadLocalSetModelVisibilities;
	private boolean hadLocalSetRenderOutlines;
	private boolean hadLocalShouldRender;
	private boolean hadLocalTransformHeldFull3DItemLayer;

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

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		String[] newInterfaces = new String[interfaces.length + 1];
		for(int i=0; i<interfaces.length; i++)
			newInterfaces[i] = interfaces[i];
		newInterfaces[interfaces.length] = "api/player/render/IRenderPlayerAPI";
		super.visit(version, access, name, signature, superName, newInterfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if(name.equals("<init>") && desc.equals(isObfuscated ? "(Lbrm;Z)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V"))
			return new RenderPlayerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated, constructorReplacements);

		if(name.equals(isObfuscated ? "d" : "bindEntityTexture") && desc.equals(isObfuscated ? "(Lrr;)Z" : "(Lnet/minecraft/entity/Entity;)Z"))
		{
			hadLocalBindEntityTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindEntityTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "bindTexture") && desc.equals(isObfuscated ? "(Lkk;)V" : "(Lnet/minecraft/util/ResourceLocation;)V"))
		{
			hadLocalBindTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindTexture", desc, signature, exceptions);
		}

		if(name.equals("canRenderName") && desc.equals(isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z"))
		{
			hadLocalCanRenderName = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanRenderName", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "doRender") && desc.equals(isObfuscated ? "(Lbmq;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V"))
		{
			hadLocalDoRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRender", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "doRenderShadowAndFire") && desc.equals(isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V"))
		{
			hadLocalDoRenderShadowAndFire = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getEntityTexture") && desc.equals(isObfuscated ? "(Lbmq;)Lkk;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;"))
		{
			hadLocalGetEntityTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEntityTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "getFontRendererFromRenderManager") && desc.equals(isObfuscated ? "()Lbct;" : "()Lnet/minecraft/client/gui/FontRenderer;"))
		{
			hadLocalGetFontRendererFromRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", desc, signature, exceptions);
		}

		if(name.equals("getMainModel") && desc.equals(isObfuscated ? "()Lbjf;" : "()Lnet/minecraft/client/model/ModelPlayer;"))
		{
			hadLocalGetMainModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "getRenderManager") && desc.equals(isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;"))
		{
			hadLocalGetRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRenderManager", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "getTeamColor") && desc.equals(isObfuscated ? "(Lrr;)I" : "(Lnet/minecraft/entity/Entity;)I"))
		{
			hadLocalGetTeamColor = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetTeamColor", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "H_" : "isMultipass") && desc.equals("()Z"))
		{
			hadLocalIsMultipass = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsMultipass", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "preRenderCallback") && desc.equals(isObfuscated ? "(Lbmq;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"))
		{
			hadLocalPreRenderCallback = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPreRenderCallback", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderEntityName") && desc.equals(isObfuscated ? "(Lbmq;DDDLjava/lang/String;D)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V"))
		{
			hadLocalRenderEntityName = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderEntityName", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "renderLeftArm") && desc.equals(isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalRenderLeftArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLeftArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLivingAt") && desc.equals(isObfuscated ? "(Lbmq;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V"))
		{
			hadLocalRenderLivingAt = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingAt", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLivingLabel") && desc.equals(isObfuscated ? "(Lrr;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V"))
		{
			hadLocalRenderLivingLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "renderMultipass") && desc.equals(isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V"))
		{
			hadLocalRenderMultipass = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderMultipass", desc, signature, exceptions);
		}

		if(name.equals("renderName") && desc.equals(isObfuscated ? "(Lsa;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V"))
		{
			hadLocalRenderName = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderName", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "renderRightArm") && desc.equals(isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalRenderRightArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderRightArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "rotateCorpse") && desc.equals(isObfuscated ? "(Lbmq;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V"))
		{
			hadLocalRotateCorpse = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotateCorpse", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "setModelVisibilities") && desc.equals(isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalSetModelVisibilities = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelVisibilities", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setRenderOutlines") && desc.equals("(Z)V"))
		{
			hadLocalSetRenderOutlines = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderOutlines", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "shouldRender") && desc.equals(isObfuscated ? "(Lrr;Lbqm;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z"))
		{
			hadLocalShouldRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localShouldRender", desc, signature, exceptions);
		}

		if(name.equals("transformHeldFull3DItemLayer") && desc.equals("()V"))
		{
			hadLocalTransformHeldFull3DItemLayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localTransformHeldFull3DItemLayer", desc, signature, exceptions);
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	@Override
	public void visitEnd()
	{
		MethodVisitor mv;

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "bindEntityTexture", "" + (isObfuscated ? "(Lrr;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "bindEntityTexture", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realBindEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "bindEntityTexture", "" + (isObfuscated ? "(Lrr;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superBindEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "bindEntityTexture", "" + (isObfuscated ? "(Lrr;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalBindEntityTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "bindEntityTexture", "" + (isObfuscated ? "(Lrr;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lkk;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "bindTexture", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/util/ResourceLocation;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lkk;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lkk;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalBindTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Lkk;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "canRenderName", "" + (isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "canRenderName", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realCanRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", "canRenderName", "" + (isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superCanRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "canRenderName", "" + (isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalCanRenderName)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "canRenderName", "" + (isObfuscated ? "(Lsa;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbmq;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRender", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbmq;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbmq;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalDoRender)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbmq;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "doRenderShadowAndFire", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDoRenderShadowAndFire", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDoRenderShadowAndFire", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalDoRenderShadowAndFire)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbmq;)Lkk;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getEntityTexture", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Lkk;" : "Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Lkk;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbmq;)Lkk;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Lkk;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbmq;)Lkk;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetEntityTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Lkk;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbmq;)Lkk;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbct;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getFontRendererFromRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbct;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lbct;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbct;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lbct;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbct;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetFontRendererFromRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lbct;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lbct;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "getMainModel", "" + (isObfuscated ? "()Lbjf;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getMainModel", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbjf;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetMainModel", "()" + (isObfuscated ? "Lbjf;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", "getMainModel", "" + (isObfuscated ? "()Lbjf;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetMainModel", "()" + (isObfuscated ? "Lbjf;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "getMainModel", "" + (isObfuscated ? "()Lbjf;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetMainModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainModel", "()" + (isObfuscated ? "Lbjf;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "getMainModel", "" + (isObfuscated ? "()Lbjf;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbrm;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetRenderManager", "()" + (isObfuscated ? "Lbrm;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetRenderManager", "()" + (isObfuscated ? "Lbrm;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRenderManager", "()" + (isObfuscated ? "Lbrm;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "getTeamColor", "" + (isObfuscated ? "(Lrr;)I" : "(Lnet/minecraft/entity/Entity;)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getTeamColor", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)I", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "getTeamColor", "" + (isObfuscated ? "(Lrr;)I" : "(Lnet/minecraft/entity/Entity;)I") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "getTeamColor", "" + (isObfuscated ? "(Lrr;)I" : "(Lnet/minecraft/entity/Entity;)I") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetTeamColor)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)I", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "getTeamColor", "" + (isObfuscated ? "(Lrr;)I" : "(Lnet/minecraft/entity/Entity;)I") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "H_" : "isMultipass", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "isMultipass", "(Lapi/player/render/IRenderPlayerAPI;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsMultipass", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "H_" : "isMultipass", "()Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsMultipass", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "H_" : "isMultipass", "()Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalIsMultipass)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsMultipass", "()Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "H_" : "isMultipass", "()Z", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbmq;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "preRenderCallback", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realPreRenderCallback", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbmq;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPreRenderCallback", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbmq;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalPreRenderCallback)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPreRenderCallback", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbmq;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderEntityName", "" + (isObfuscated ? "(Lbmq;DDDLjava/lang/String;D)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.DLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderEntityName", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderEntityName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.DLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderEntityName", "" + (isObfuscated ? "(Lbmq;DDDLjava/lang/String;D)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderEntityName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.DLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderEntityName", "" + (isObfuscated ? "(Lbmq;DDDLjava/lang/String;D)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderEntityName)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderEntityName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.ALOAD, 8);
			mv.visitVarInsn(Opcodes.DLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderEntityName", "" + (isObfuscated ? "(Lbmq;DDDLjava/lang/String;D)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;D)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLeftArm", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLeftArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbmq;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLivingAt", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLivingAt", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbmq;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLivingAt", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbmq;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLivingAt)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingAt", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbmq;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lrr;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLivingLabel", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;Ljava/lang/String;DDDI)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Ljava/lang/String;DDDI)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lrr;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Ljava/lang/String;DDDI)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitVarInsn(Opcodes.ILOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lrr;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLivingLabel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Ljava/lang/String;DDDI)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 3);
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			mv.visitVarInsn(Opcodes.DLOAD, 7);
			mv.visitVarInsn(Opcodes.ILOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lrr;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "renderMultipass", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderMultipass", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderMultipass", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderMultipass", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderMultipass", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "b" : "renderMultipass", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderMultipass)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderMultipass", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "b" : "renderMultipass", "" + (isObfuscated ? "(Lrr;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "renderName", "" + (isObfuscated ? "(Lsa;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderName", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", "renderName", "" + (isObfuscated ? "(Lsa;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "renderName", "" + (isObfuscated ? "(Lsa;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderName)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "renderName", "" + (isObfuscated ? "(Lsa;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderRightArm", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderRightArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbmq;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "rotateCorpse", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRotateCorpse", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbmq;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRotateCorpse", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbmq;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRotateCorpse)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotateCorpse", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbmq;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setModelVisibilities", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetModelVisibilities)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbmq;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setRenderOutlines", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setRenderOutlines", "(Lapi/player/render/IRenderPlayerAPI;Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetRenderOutlines", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderOutlines", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetRenderOutlines)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderOutlines", "(Z)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lrr;Lbqm;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "shouldRender", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realShouldRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lrr;Lbqm;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superShouldRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 3);
		mv.visitVarInsn(Opcodes.DLOAD, 5);
		mv.visitVarInsn(Opcodes.DLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lrr;Lbqm;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalShouldRender)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localShouldRender", "(Lnet/minecraft/client/entity/AbstractClientPlayer;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.ALOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 3);
			mv.visitVarInsn(Opcodes.DLOAD, 5);
			mv.visitVarInsn(Opcodes.DLOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lrr;Lbqm;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "transformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "transformHeldFull3DItemLayer", "(Lapi/player/render/IRenderPlayerAPI;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realTransformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", "transformHeldFull3DItemLayer", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superTransformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "transformHeldFull3DItemLayer", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalTransformHeldFull3DItemLayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localTransformHeldFull3DItemLayer", "()V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bsd" : "net/minecraft/client/renderer/entity/RenderLivingBase", "transformHeldFull3DItemLayer", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderManagerField", isObfuscated ? "()Lbrm;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "renderManager", isObfuscated ? "Lbrm;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderOutlinesField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "renderOutlines", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderOutlinesField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "renderOutlines", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowOpaqueField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowOpaqueField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowSizeField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowSizeField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowSize", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSmallArmsField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "smallArms", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSmallArmsField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "smallArms", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBase", "(Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBase", "(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;)Lapi/player/render/RenderPlayerBase;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerBaseIds", "()Ljava/util/Set;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderPlayerBaseIds", "(Lapi/player/render/IRenderPlayerAPI;)Ljava/util/Set;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "dynamic", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.ALOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "dynamic", "(Lapi/player/render/IRenderPlayerAPI;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayerAPI", "()Lapi/player/render/RenderPlayerAPI;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "buk" : "net/minecraft/client/renderer/entity/RenderPlayer", "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayer", isObfuscated ? "()Lbuk;" : "()Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getAllInstances", isObfuscated ? "()[Lbuk;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getAllInstances", isObfuscated ? "()[Lbuk;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		cv.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;", null, null);
	}
}
