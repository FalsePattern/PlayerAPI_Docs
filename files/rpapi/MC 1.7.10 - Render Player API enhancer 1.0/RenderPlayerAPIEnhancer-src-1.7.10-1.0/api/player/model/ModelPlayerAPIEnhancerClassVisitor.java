package api.player.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.objectweb.asm.*;
import org.apache.logging.log4j.*;

public class ModelPlayerAPIEnhancerClassVisitor extends ClassVisitor
{
	public static byte[] transform(byte[] bytes, boolean isObfuscated)
	{
		try
		{
			ByteArrayInputStream in = new ByteArrayInputStream(bytes);
			ClassReader cr = new ClassReader(in);
			ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			ModelPlayerAPIEnhancerClassVisitor p = new ModelPlayerAPIEnhancerClassVisitor(cw, isObfuscated);

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

	public ModelPlayerAPIEnhancerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated)
	{
		super(262144, classVisitor);
		this.isObfuscated = isObfuscated;
	}

	private final boolean isObfuscated;
	private String enhancableClassName;

	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces)
	{
		if((isObfuscated && superName.equals("bhm")) || superName.equals("net/minecraft/client/model/ModelBiped"))
		{
			enhancableClassName = name.replace('/', '.');
			String newSuperName = superName.equals("bhm") ? "api/player/model/ModelPlayer" : "api/player/model/ModelPlayer";
			info("enhances class '%s' to extend class '%s' instead of class '%s'", enhancableClassName, newSuperName.replace('/', '.'), superName.replace('/', '.'));
			superName = newSuperName;
		}
		else
			info("leaves class '%s' untouched because it extends the unrelated class '%s'", name.replace('/', '.'), superName.replace('/', '.'));

		super.visit(version, access, name, signature, superName, interfaces);
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions)
	{
		if(enhancableClassName != null && !name.equals("<cinit>"))
		{
			if(name.equals("<init>"))
			{
				info("modifies constructor '%s%s'", enhancableClassName, desc);
				return new ModelPlayerAPIEnhancerConstructorVisitor(super.visitMethod(access, name, desc, signature, exceptions), isObfuscated);
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/util/Random;)Lbix;"))
				{
					info("removes method '%s.%s%s' because it actually is 'getRandomModelBox'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_85181_a") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
				{
					info("removes method '%s.%s%s' because it actually is 'getRandomModelBox'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("getRandomModelBox") && desc.equals("(Ljava/util/Random;)Lnet/minecraft/client/model/ModelRenderer;"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/lang/String;)Lbiy;"))
				{
					info("removes method '%s.%s%s' because it actually is 'getTextureOffset'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78084_a") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
				{
					info("removes method '%s.%s%s' because it actually is 'getTextureOffset'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("getTextureOffset") && desc.equals("(Ljava/lang/String;)Lnet/minecraft/client/model/TextureOffset;"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Lsa;FFFFFF)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'render'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78088_a") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'render'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("render") && desc.equals("(Lnet/minecraft/entity/Entity;FFFFFF)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("c") && desc.equals("(F)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'renderCloak'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78111_c") && desc.equals("(F)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'renderCloak'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("renderCloak") && desc.equals("(F)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("b") && desc.equals("(F)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'renderEars'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78110_b") && desc.equals("(F)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'renderEars'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("renderEars") && desc.equals("(F)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Lsv;FFF)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setLivingAnimations'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78086_a") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setLivingAnimations'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("setLivingAnimations") && desc.equals("(Lnet/minecraft/entity/EntityLivingBase;FFF)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(FFFFFFLsa;)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setRotationAngles'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78087_a") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setRotationAngles'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("setRotationAngles") && desc.equals("(FFFFFFLnet/minecraft/entity/Entity;)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			if(isObfuscated)
			{
				if(name.equals("a") && desc.equals("(Ljava/lang/String;II)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setTextureOffset'", enhancableClassName, name, desc);
					return null;
				}
				else if(name.equals("func_78085_a") && desc.equals("(Ljava/lang/String;II)V"))
				{
					info("removes method '%s.%s%s' because it actually is 'setTextureOffset'", enhancableClassName, name, desc);
					return null;
				}
			}
			else if(name.equals("setTextureOffset") && desc.equals("(Ljava/lang/String;II)V"))
			{
				info("removes method '%s.%s%s'", enhancableClassName, name, desc);
				return null;
			}

			info("leaves unknown method '%s.%s%s' untouched", enhancableClassName, name, desc);
		}
		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	public static void info(String format, Object... args)
	{
		log(Level.INFO, "Render Player API enhancer " + format, args);
	}

	public static void log(Level level, String format, Object... args)
	{
		LogManager.getLogger("RenderPlayerAPIEnhancer").log(level, String.format(format, args));
	}
}
