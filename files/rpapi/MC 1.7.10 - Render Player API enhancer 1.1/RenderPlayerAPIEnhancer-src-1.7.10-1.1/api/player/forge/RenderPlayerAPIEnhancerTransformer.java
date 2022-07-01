package api.player.forge;

import net.minecraft.launchwrapper.*;

import api.player.model.*;

public class RenderPlayerAPIEnhancerTransformer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		for(String pattern : RenderPlayerAPIEnhancerPlugin.canonicalClassNamePatterns)
			if(transformedName.matches(pattern))
			{
				ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class because it matches the pattern '%s'", transformedName, pattern);
				return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
			}
		return bytes;
	}
}