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

	private boolean hadLocalAddLayer;
	private boolean hadLocalBindEntityTexture;
	private boolean hadLocalBindTexture;
	private boolean hadLocalCanRenderName;
	private boolean hadLocalDoRender;
	private boolean hadLocalDoRenderShadowAndFire;
	private boolean hadLocalGetColorMultiplier;
	private boolean hadLocalGetDeathMaxRotation;
	private boolean hadLocalGetEntityTexture;
	private boolean hadLocalGetFontRendererFromRenderManager;
	private boolean hadLocalGetMainModel;
	private boolean hadLocalGetRenderManager;
	private boolean hadLocalGetSwingProgress;
	private boolean hadLocalHandleRotationFloat;
	private boolean hadLocalInterpolateRotation;
	private boolean hadLocalPreRenderCallback;
	private boolean hadLocalRemoveLayer;
	private boolean hadLocalRenderLayers;
	private boolean hadLocalRenderLeftArm;
	private boolean hadLocalRenderLivingAt;
	private boolean hadLocalRenderLivingLabel;
	private boolean hadLocalRenderModel;
	private boolean hadLocalRenderName;
	private boolean hadLocalRenderOffsetLivingLabel;
	private boolean hadLocalRenderRightArm;
	private boolean hadLocalRotateCorpse;
	private boolean hadLocalSetBrightness;
	private boolean hadLocalSetDoRenderBrightness;
	private boolean hadLocalSetModelVisibilities;
	private boolean hadLocalSetRenderOutlines;
	private boolean hadLocalSetScoreTeamColor;
	private boolean hadLocalShouldRender;
	private boolean hadLocalTransformHeldFull3DItemLayer;
	private boolean hadLocalUnsetBrightness;
	private boolean hadLocalUnsetScoreTeamColor;

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
		if(name.equals("<init>") && desc.equals(isObfuscated ? "(Lbiu;Z)V" : "(Lnet/minecraft/client/renderer/entity/RenderManager;Z)V"))
			return new RenderPlayerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated, constructorReplacements);

		if(name.equals(isObfuscated ? "a" : "addLayer") && desc.equals(isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z"))
		{
			hadLocalAddLayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddLayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "bindEntityTexture") && desc.equals(isObfuscated ? "(Lpk;)Z" : "(Lnet/minecraft/entity/Entity;)Z"))
		{
			hadLocalBindEntityTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindEntityTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "bindTexture") && desc.equals(isObfuscated ? "(Ljy;)V" : "(Lnet/minecraft/util/ResourceLocation;)V"))
		{
			hadLocalBindTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "canRenderName") && desc.equals(isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z"))
		{
			hadLocalCanRenderName = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanRenderName", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "doRender") && desc.equals(isObfuscated ? "(Lbet;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V"))
		{
			hadLocalDoRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRender", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "doRenderShadowAndFire") && desc.equals(isObfuscated ? "(Lpk;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V"))
		{
			hadLocalDoRenderShadowAndFire = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDoRenderShadowAndFire", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getColorMultiplier") && desc.equals(isObfuscated ? "(Lpr;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I"))
		{
			hadLocalGetColorMultiplier = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "getDeathMaxRotation") && desc.equals(isObfuscated ? "(Lpr;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F"))
		{
			hadLocalGetDeathMaxRotation = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "getEntityTexture") && desc.equals(isObfuscated ? "(Lbet;)Ljy;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;"))
		{
			hadLocalGetEntityTexture = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEntityTexture", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "getFontRendererFromRenderManager") && desc.equals(isObfuscated ? "()Lavn;" : "()Lnet/minecraft/client/gui/FontRenderer;"))
		{
			hadLocalGetFontRendererFromRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", desc, signature, exceptions);
		}

		if(name.equals("getMainModel") && desc.equals(isObfuscated ? "()Lbbr;" : "()Lnet/minecraft/client/model/ModelPlayer;"))
		{
			hadLocalGetMainModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "getRenderManager") && desc.equals(isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;"))
		{
			hadLocalGetRenderManager = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRenderManager", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "getSwingProgress") && desc.equals(isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F"))
		{
			hadLocalGetSwingProgress = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetSwingProgress", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "handleRotationFloat") && desc.equals(isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F"))
		{
			hadLocalHandleRotationFloat = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "interpolateRotation") && desc.equals("(FFF)F"))
		{
			hadLocalInterpolateRotation = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInterpolateRotation", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "preRenderCallback") && desc.equals(isObfuscated ? "(Lbet;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V"))
		{
			hadLocalPreRenderCallback = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPreRenderCallback", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "removeLayer") && desc.equals(isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z"))
		{
			hadLocalRemoveLayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRemoveLayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLayers") && desc.equals(isObfuscated ? "(Lpr;FFFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V"))
		{
			hadLocalRenderLayers = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLayers", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "renderLeftArm") && desc.equals(isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalRenderLeftArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLeftArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLivingAt") && desc.equals(isObfuscated ? "(Lbet;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V"))
		{
			hadLocalRenderLivingAt = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingAt", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderLivingLabel") && desc.equals(isObfuscated ? "(Lpk;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V"))
		{
			hadLocalRenderLivingLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLivingLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderModel") && desc.equals(isObfuscated ? "(Lpr;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V"))
		{
			hadLocalRenderModel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "renderName") && desc.equals(isObfuscated ? "(Lpr;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V"))
		{
			hadLocalRenderName = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderName", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "renderOffsetLivingLabel") && desc.equals(isObfuscated ? "(Lbet;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V"))
		{
			hadLocalRenderOffsetLivingLabel = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderOffsetLivingLabel", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "b" : "renderRightArm") && desc.equals(isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalRenderRightArm = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderRightArm", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "rotateCorpse") && desc.equals(isObfuscated ? "(Lbet;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V"))
		{
			hadLocalRotateCorpse = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRotateCorpse", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setBrightness") && desc.equals(isObfuscated ? "(Lpr;FZ)Z" : "(Lnet/minecraft/entity/EntityLivingBase;FZ)Z"))
		{
			hadLocalSetBrightness = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetBrightness", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "setDoRenderBrightness") && desc.equals(isObfuscated ? "(Lpr;F)Z" : "(Lnet/minecraft/entity/EntityLivingBase;F)Z"))
		{
			hadLocalSetDoRenderBrightness = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetDoRenderBrightness", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "d" : "setModelVisibilities") && desc.equals(isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V"))
		{
			hadLocalSetModelVisibilities = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelVisibilities", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "setRenderOutlines") && desc.equals("(Z)V"))
		{
			hadLocalSetRenderOutlines = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderOutlines", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "c" : "setScoreTeamColor") && desc.equals(isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z"))
		{
			hadLocalSetScoreTeamColor = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetScoreTeamColor", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "a" : "shouldRender") && desc.equals(isObfuscated ? "(Lpk;Lbia;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z"))
		{
			hadLocalShouldRender = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localShouldRender", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "C_" : "transformHeldFull3DItemLayer") && desc.equals("()V"))
		{
			hadLocalTransformHeldFull3DItemLayer = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localTransformHeldFull3DItemLayer", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "f" : "unsetBrightness") && desc.equals("()V"))
		{
			hadLocalUnsetBrightness = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUnsetBrightness", desc, signature, exceptions);
		}

		if(name.equals(isObfuscated ? "e" : "unsetScoreTeamColor") && desc.equals("()V"))
		{
			hadLocalUnsetScoreTeamColor = true;
			return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUnsetScoreTeamColor", desc, signature, exceptions);
		}

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	@Override
	public void visitEnd()
	{
		MethodVisitor mv;

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "addLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "addLayer", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realAddLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "addLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superAddLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "addLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalAddLayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "addLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "bindEntityTexture", "" + (isObfuscated ? "(Lpk;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "bindEntityTexture", "" + (isObfuscated ? "(Lpk;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superBindEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "bindEntityTexture", "" + (isObfuscated ? "(Lpk;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalBindEntityTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "bindEntityTexture", "" + (isObfuscated ? "(Lpk;)Z" : "(Lnet/minecraft/entity/Entity;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Ljy;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "bindTexture", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/util/ResourceLocation;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Ljy;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Ljy;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalBindTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localBindTexture", "(Lnet/minecraft/util/ResourceLocation;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "bindTexture", "" + (isObfuscated ? "(Ljy;)V" : "(Lnet/minecraft/util/ResourceLocation;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "canRenderName", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "canRenderName", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superCanRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "canRenderName", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalCanRenderName)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "canRenderName", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbet;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbet;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
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
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbet;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "doRender", "" + (isObfuscated ? "(Lbet;DDDFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lpk;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lpk;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
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
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lpk;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "doRenderShadowAndFire", "" + (isObfuscated ? "(Lpk;DDDFF)V" : "(Lnet/minecraft/entity/Entity;DDDFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lpr;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getColorMultiplier", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;FF)I", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetColorMultiplier", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FF)I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lpr;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetColorMultiplier", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FF)I", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lpr;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetColorMultiplier)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetColorMultiplier", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FF)I", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getColorMultiplier", "" + (isObfuscated ? "(Lpr;FF)I" : "(Lnet/minecraft/entity/EntityLivingBase;FF)I") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lpr;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getDeathMaxRotation", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetDeathMaxRotation", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lpr;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetDeathMaxRotation", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lpr;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetDeathMaxRotation)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDeathMaxRotation", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)F", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "getDeathMaxRotation", "" + (isObfuscated ? "(Lpr;)F" : "(Lnet/minecraft/entity/EntityLivingBase;)F") + "", false);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbet;)Ljy;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getEntityTexture", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Ljy;" : "Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Ljy;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbet;)Ljy;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Ljy;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbet;)Ljy;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetEntityTexture)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEntityTexture", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)" + (isObfuscated ? "Ljy;" : "Lnet/minecraft/util/ResourceLocation;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "getEntityTexture", "" + (isObfuscated ? "(Lbet;)Ljy;" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Lnet/minecraft/util/ResourceLocation;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lavn;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getFontRendererFromRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lavn;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lavn;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lavn;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lavn;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lavn;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetFontRendererFromRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetFontRendererFromRenderManager", "()" + (isObfuscated ? "Lavn;" : "Lnet/minecraft/client/gui/FontRenderer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "getFontRendererFromRenderManager", "" + (isObfuscated ? "()Lavn;" : "()Lnet/minecraft/client/gui/FontRenderer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, "getMainModel", "" + (isObfuscated ? "()Lbbr;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getMainModel", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbbr;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetMainModel", "()" + (isObfuscated ? "Lbbr;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", "getMainModel", "" + (isObfuscated ? "()Lbbr;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetMainModel", "()" + (isObfuscated ? "Lbbr;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", "getMainModel", "" + (isObfuscated ? "()Lbbr;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetMainModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetMainModel", "()" + (isObfuscated ? "Lbbr;" : "Lnet/minecraft/client/model/ModelPlayer;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", "getMainModel", "" + (isObfuscated ? "()Lbbr;" : "()Lnet/minecraft/client/model/ModelPlayer;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getRenderManager", "(Lapi/player/render/IRenderPlayerAPI;)" + (isObfuscated ? "Lbiu;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetRenderManager", "()" + (isObfuscated ? "Lbiu;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetRenderManager", "()" + (isObfuscated ? "Lbiu;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetRenderManager)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetRenderManager", "()" + (isObfuscated ? "Lbiu;" : "Lnet/minecraft/client/renderer/entity/RenderManager;") + "", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "getRenderManager", "" + (isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;") + "", false);
			mv.visitInsn(Opcodes.ARETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "getSwingProgress", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getSwingProgress", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetSwingProgress", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "getSwingProgress", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetSwingProgress", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "getSwingProgress", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalGetSwingProgress)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetSwingProgress", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "getSwingProgress", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "handleRotationFloat", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realHandleRotationFloat", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superHandleRotationFloat", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalHandleRotationFloat)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHandleRotationFloat", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)F", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "handleRotationFloat", "" + (isObfuscated ? "(Lpr;F)F" : "(Lnet/minecraft/entity/EntityLivingBase;F)F") + "", false);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "interpolateRotation", "(FFF)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "interpolateRotation", "(Lapi/player/render/IRenderPlayerAPI;FFF)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realInterpolateRotation", "(FFF)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "interpolateRotation", "(FFF)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superInterpolateRotation", "(FFF)F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "interpolateRotation", "(FFF)F", false);
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalInterpolateRotation)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localInterpolateRotation", "(FFF)F", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.FLOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "interpolateRotation", "(FFF)F", false);
			mv.visitInsn(Opcodes.FRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbet;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbet;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superPreRenderCallback", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbet;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalPreRenderCallback)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localPreRenderCallback", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "preRenderCallback", "" + (isObfuscated ? "(Lbet;F)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "removeLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "removeLayer", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRemoveLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "removeLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRemoveLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "removeLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRemoveLayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRemoveLayer", "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "removeLayer", "" + (isObfuscated ? "(Lblb;)Z" : "(Lnet/minecraft/client/renderer/entity/layers/LayerRenderer;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLayers", "" + (isObfuscated ? "(Lpr;FFFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLayers", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLayers", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLayers", "" + (isObfuscated ? "(Lpr;FFFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLayers", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitVarInsn(Opcodes.FLOAD, 8);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLayers", "" + (isObfuscated ? "(Lpr;FFFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLayers)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLayers", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 7);
			mv.visitVarInsn(Opcodes.FLOAD, 8);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLayers", "" + (isObfuscated ? "(Lpr;FFFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderLeftArm", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderLeftArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderLeftArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "renderLeftArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbet;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbet;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderLivingAt", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbet;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingAt", "" + (isObfuscated ? "(Lbet;DDD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lpk;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lpk;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
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
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lpk;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderLivingLabel", "" + (isObfuscated ? "(Lpk;Ljava/lang/String;DDDI)V" : "(Lnet/minecraft/entity/Entity;Ljava/lang/String;DDDI)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lpr;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderModel", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFF)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderModel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lpr;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderModel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitVarInsn(Opcodes.FLOAD, 5);
		mv.visitVarInsn(Opcodes.FLOAD, 6);
		mv.visitVarInsn(Opcodes.FLOAD, 7);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lpr;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderModel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderModel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFFFFF)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.FLOAD, 3);
			mv.visitVarInsn(Opcodes.FLOAD, 4);
			mv.visitVarInsn(Opcodes.FLOAD, 5);
			mv.visitVarInsn(Opcodes.FLOAD, 6);
			mv.visitVarInsn(Opcodes.FLOAD, 7);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderModel", "" + (isObfuscated ? "(Lpr;FFFFFF)V" : "(Lnet/minecraft/entity/EntityLivingBase;FFFFFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "renderName", "" + (isObfuscated ? "(Lpr;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderName", "" + (isObfuscated ? "(Lpr;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderName", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "renderName", "" + (isObfuscated ? "(Lpr;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "renderName", "" + (isObfuscated ? "(Lpr;DDD)V" : "(Lnet/minecraft/entity/EntityLivingBase;DDD)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "renderOffsetLivingLabel", "" + (isObfuscated ? "(Lbet;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderOffsetLivingLabel", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderOffsetLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "renderOffsetLivingLabel", "" + (isObfuscated ? "(Lbet;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderOffsetLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.DLOAD, 2);
		mv.visitVarInsn(Opcodes.DLOAD, 4);
		mv.visitVarInsn(Opcodes.DLOAD, 6);
		mv.visitVarInsn(Opcodes.ALOAD, 8);
		mv.visitVarInsn(Opcodes.FLOAD, 9);
		mv.visitVarInsn(Opcodes.DLOAD, 10);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderOffsetLivingLabel", "" + (isObfuscated ? "(Lbet;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderOffsetLivingLabel)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderOffsetLivingLabel", "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.DLOAD, 2);
			mv.visitVarInsn(Opcodes.DLOAD, 4);
			mv.visitVarInsn(Opcodes.DLOAD, 6);
			mv.visitVarInsn(Opcodes.ALOAD, 8);
			mv.visitVarInsn(Opcodes.FLOAD, 9);
			mv.visitVarInsn(Opcodes.DLOAD, 10);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "renderOffsetLivingLabel", "" + (isObfuscated ? "(Lbet;DDDLjava/lang/String;FD)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;DDDLjava/lang/String;FD)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "renderRightArm", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalRenderRightArm)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localRenderRightArm", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "b" : "renderRightArm", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbet;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbet;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superRotateCorpse", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.FLOAD, 3);
		mv.visitVarInsn(Opcodes.FLOAD, 4);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbet;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "rotateCorpse", "" + (isObfuscated ? "(Lbet;FFF)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;FFF)V") + "", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "setBrightness", "" + (isObfuscated ? "(Lpr;FZ)Z" : "(Lnet/minecraft/entity/EntityLivingBase;FZ)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setBrightness", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;FZ)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FZ)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "setBrightness", "" + (isObfuscated ? "(Lpr;FZ)Z" : "(Lnet/minecraft/entity/EntityLivingBase;FZ)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FZ)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitVarInsn(Opcodes.ILOAD, 3);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setBrightness", "" + (isObfuscated ? "(Lpr;FZ)Z" : "(Lnet/minecraft/entity/EntityLivingBase;FZ)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetBrightness)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;FZ)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitVarInsn(Opcodes.ILOAD, 3);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setBrightness", "" + (isObfuscated ? "(Lpr;FZ)Z" : "(Lnet/minecraft/entity/EntityLivingBase;FZ)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "setDoRenderBrightness", "" + (isObfuscated ? "(Lpr;F)Z" : "(Lnet/minecraft/entity/EntityLivingBase;F)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setDoRenderBrightness", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;F)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetDoRenderBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "setDoRenderBrightness", "" + (isObfuscated ? "(Lpr;F)Z" : "(Lnet/minecraft/entity/EntityLivingBase;F)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetDoRenderBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitVarInsn(Opcodes.FLOAD, 2);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "setDoRenderBrightness", "" + (isObfuscated ? "(Lpr;F)Z" : "(Lnet/minecraft/entity/EntityLivingBase;F)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetDoRenderBrightness)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetDoRenderBrightness", "(Lnet/minecraft/client/entity/AbstractClientPlayer;F)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitVarInsn(Opcodes.FLOAD, 2);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "setDoRenderBrightness", "" + (isObfuscated ? "(Lpr;F)Z" : "(Lnet/minecraft/entity/EntityLivingBase;F)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setModelVisibilities", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetModelVisibilities)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetModelVisibilities", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "d" : "setModelVisibilities", "" + (isObfuscated ? "(Lbet;)V" : "(Lnet/minecraft/client/entity/AbstractClientPlayer;)V") + "", false);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetRenderOutlines", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetRenderOutlines)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetRenderOutlines", "(Z)V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ILOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "setRenderOutlines", "(Z)V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "c" : "setScoreTeamColor", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitTypeInsn(Opcodes.CHECKCAST, "net/minecraft/client/entity/AbstractClientPlayer");
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "setScoreTeamColor", "(Lapi/player/render/IRenderPlayerAPI;Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetScoreTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "setScoreTeamColor", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetScoreTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "setScoreTeamColor", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalSetScoreTeamColor)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetScoreTeamColor", "(Lnet/minecraft/client/entity/AbstractClientPlayer;)Z", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitVarInsn(Opcodes.ALOAD, 1);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "c" : "setScoreTeamColor", "" + (isObfuscated ? "(Lpr;)Z" : "(Lnet/minecraft/entity/EntityLivingBase;)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lpk;Lbia;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", null, null);
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
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lpk;Lbia;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
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
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lpk;Lbia;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
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
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "a" : "shouldRender", "" + (isObfuscated ? "(Lpk;Lbia;DDD)Z" : "(Lnet/minecraft/entity/Entity;Lnet/minecraft/client/renderer/culling/ICamera;DDD)Z") + "", false);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "C_" : "transformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "transformHeldFull3DItemLayer", "(Lapi/player/render/IRenderPlayerAPI;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realTransformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "C_" : "transformHeldFull3DItemLayer", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superTransformHeldFull3DItemLayer", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "C_" : "transformHeldFull3DItemLayer", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalTransformHeldFull3DItemLayer)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localTransformHeldFull3DItemLayer", "()V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "C_" : "transformHeldFull3DItemLayer", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "f" : "unsetBrightness", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "unsetBrightness", "(Lapi/player/render/IRenderPlayerAPI;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUnsetBrightness", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "unsetBrightness", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUnsetBrightness", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "f" : "unsetBrightness", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalUnsetBrightness)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUnsetBrightness", "()V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "f" : "unsetBrightness", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC, isObfuscated ? "e" : "unsetScoreTeamColor", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "unsetScoreTeamColor", "(Lapi/player/render/IRenderPlayerAPI;)V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUnsetScoreTeamColor", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "e" : "unsetScoreTeamColor", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUnsetScoreTeamColor", "()V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "e" : "unsetScoreTeamColor", "()V", false);
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		if(!hadLocalUnsetScoreTeamColor)
		{
			mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUnsetScoreTeamColor", "()V", null, null);
			mv.visitVarInsn(Opcodes.ALOAD, 0);
			mv.visitMethodInsn(Opcodes.INVOKESPECIAL, isObfuscated ? "bjl" : "net/minecraft/client/renderer/entity/RendererLivingEntity", isObfuscated ? "e" : "unsetScoreTeamColor", "()V", false);
			mv.visitInsn(Opcodes.RETURN);
			mv.visitMaxs(0, 0);
			mv.visitEnd();
		}

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getBrightnessBufferField", "()Ljava/nio/FloatBuffer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "g" : "brightnessBuffer", "Ljava/nio/FloatBuffer;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setBrightnessBufferField", "(Ljava/nio/FloatBuffer;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "g" : "brightnessBuffer", "Ljava/nio/FloatBuffer;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLayerRenderersField", "()Ljava/util/List;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "h" : "layerRenderers", "Ljava/util/List;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLayerRenderersField", "(Ljava/util/List;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "h" : "layerRenderers", "Ljava/util/List;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMainModelField", isObfuscated ? "()Lbbo;" : "()Lnet/minecraft/client/model/ModelBase;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "mainModel", isObfuscated ? "Lbbo;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMainModelField", isObfuscated ? "(Lbbo;)V" : "(Lnet/minecraft/client/model/ModelBase;)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ALOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "f" : "mainModel", isObfuscated ? "Lbbo;" : "Lnet/minecraft/client/model/ModelBase;");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderManagerField", isObfuscated ? "()Lbiu;" : "()Lnet/minecraft/client/renderer/entity/RenderManager;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "b" : "renderManager", isObfuscated ? "Lbiu;" : "Lnet/minecraft/client/renderer/entity/RenderManager;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderOutlinesField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "i" : "renderOutlines", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderOutlinesField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "i" : "renderOutlines", "Z");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowOpaqueField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowOpaqueField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "d" : "shadowOpaque", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getShadowSizeField", "()F", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "shadowSize", "F");
		mv.visitInsn(Opcodes.FRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setShadowSizeField", "(F)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.FLOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "c" : "shadowSize", "F");
		mv.visitInsn(Opcodes.RETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSmallArmsField", "()Z", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "smallArms", "Z");
		mv.visitInsn(Opcodes.IRETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSmallArmsField", "(Z)V", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitVarInsn(Opcodes.ILOAD, 1);
		mv.visitFieldInsn(Opcodes.PUTFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", isObfuscated ? "a" : "smallArms", "Z");
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
		mv.visitFieldInsn(Opcodes.GETFIELD, isObfuscated ? "bln" : "net/minecraft/client/renderer/entity/RenderPlayer", "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;");
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderPlayer", isObfuscated ? "()Lbln;" : "()Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitVarInsn(Opcodes.ALOAD, 0);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		mv = cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC, "getAllInstances", isObfuscated ? "()[Lbln;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", null, null);
		mv.visitMethodInsn(Opcodes.INVOKESTATIC, "api/player/render/RenderPlayerAPI", "getAllInstances", isObfuscated ? "()[Lbln;" : "()[Lnet/minecraft/client/renderer/entity/RenderPlayer;", false);
		mv.visitInsn(Opcodes.ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		cv.visitField(Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL, "renderPlayerAPI", "Lapi/player/render/RenderPlayerAPI;", null, null);
	}
}
