package api.player.forge;

import net.minecraft.launchwrapper.*;
import api.player.model.*;
import api.player.render.*;

public class RenderPlayerAPITransformer implements IClassTransformer
{
	public byte[] transform(String name, String transformedName, byte[] bytes)
	{
		if(transformedName.equals(RenderPlayerClassVisitor.targetClassName))
			return RenderPlayerClassVisitor.transform(bytes, RenderPlayerAPIPlugin.isObfuscated);
		else if(transformedName.equals(ModelPlayerClassVisitor.targetClassName))
			return ModelPlayerClassVisitor.transform(bytes, RenderPlayerAPIPlugin.isObfuscated);
		else
			return bytes;
	}
}