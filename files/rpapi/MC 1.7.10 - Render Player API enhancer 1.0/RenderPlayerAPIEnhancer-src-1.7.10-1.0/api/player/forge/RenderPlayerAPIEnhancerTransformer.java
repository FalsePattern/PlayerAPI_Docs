package api.player.forge;

import net.minecraft.launchwrapper.*;

import api.player.model.*;

public class RenderPlayerAPIEnhancerTransformer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if(transformedName.toLowerCase().contains("armor") || transformedName.toLowerCase().contains("armour"))
		{
			ModelPlayerAPIEnhancerClassVisitor.info("detects class '%s' that might be a player armor class", transformedName);
			return ModelPlayerAPIEnhancerClassVisitor.transform(bytes, RenderPlayerAPIEnhancerPlugin.isObfuscated);
		}
		return bytes;
	}
}