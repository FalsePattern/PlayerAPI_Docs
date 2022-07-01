package api.player.forge;

import java.io.*;
import java.util.*;

import org.apache.logging.log4j.*;

import cpw.mods.fml.relauncher.*;
import api.player.model.*;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("api.player.forge")
public class RenderPlayerAPIEnhancerPlugin implements IFMLLoadingPlugin
{
	public static String Version = "1.1";

	public static boolean isObfuscated;
	public static boolean hasRenderPlayerAPI = false;
	public static boolean hasValidRenderPlayerAPI = false;
	public static Set<String> canonicalClassNamePatterns;

	private static final String[] defaultPatterns = new String[]
	{
		"(?i).*armor.*",
		"(?i).*armour.*",
		"thaumic\\.tinkerer\\.client\\.model\\.kami\\.ModelWings",
		"thaumcraft\\.client\\.renderers\\.models\\.gear\\.ModelRobe",
		"thaumcraft\\.client\\.renderers\\.models\\.gear\\.ModelHoverHarness"
	};

	public RenderPlayerAPIEnhancerPlugin()
	{
		for(String fileName : CoreModManager.getLoadedCoremods())
			if(fileName.startsWith("RenderPlayerAPI-") && fileName.endsWith(".jar"))
			{
				hasRenderPlayerAPI = true;
				String[] versions = fileName.substring(16, fileName.length() - 4).split("-");
				String renderPlayerAPIVersion = versions[versions.length - 1];
				hasValidRenderPlayerAPI = !renderPlayerAPIVersion.equals("1.0");
				break;
			}

		if(hasRenderPlayerAPI)
			if (hasValidRenderPlayerAPI)
				log(Level.WARN,
					"========================================",
					"The mod \"Render Player API enhancer\" version " + Version + " is active!",
					"----------------------------------------",
					"Render Player API enhancer will violently force ALL classes that:",
					"* extend 'net.minecraft.client.model.ModelBiped' and",
					"* have either of the case independent texts 'armor' or 'armour' in their names",
					"to use Render Player API.",
					"----------------------------------------",
					"This can fix various incompatibilities between:",
					"* player animation changing mods and ",
					"* player armor model adding mods.",
					"This can also screw everything up, so use with caution.",
					"========================================");
			else
				log(Level.ERROR,
					"========================================",
					"The mod \"Render Player API enhancer\" version " + Version + " can not be created!",
					"----------------------------------------",
					"The version of the found \"Render Player API\" was not high enough so the mod \"Render Player API enhancer\" will remain inert.",
					"To activate \"Render Player API enhancer\" download and install the latest Render Player API Core for the Minecraft version you were trying to run.",
					"========================================");
		else
			log(Level.ERROR,
				"========================================",
				"The mod \"Render Player API enhancer\" version " + Version + " can not be created!",
				"----------------------------------------",
				"The API \"Render Player API\" was not found so the mod \"Render Player API enhancer\" will remain inert.",
				"To activate \"Render Player API enhancer\" download and install the latest Render Player API Core for the Minecraft version you were trying to run.",
				"========================================");
	}

	public String[] getASMTransformerClass()
	{
		return hasRenderPlayerAPI ? new String[] { "api.player.forge.RenderPlayerAPIEnhancerTransformer" } : null;
	}

	public String getModContainerClass()
	{
		return hasRenderPlayerAPI ? "api.player.forge.RenderPlayerAPIEnhancerContainer" : null;
	}

	public String getSetupClass()
	{
		return null;
	}

	public void injectData(Map<String, Object> data)
	{
		isObfuscated = (Boolean)data.get("runtimeDeobfuscationEnabled");
		if(hasRenderPlayerAPI)
		{
			try
			{
				File mcLocation = (File)data.get("mcLocation");
				File optionsFile = new File(mcLocation, "render_player_api_enhancer_patterns.txt");
				if(!optionsFile.exists())
				{
					PrintWriter printer = new PrintWriter(new FileWriter(optionsFile));
					for(String defaultPattern : defaultPatterns)
						printer.println(defaultPattern);
					printer.close();
				}

				BufferedReader reader = new BufferedReader(new FileReader(optionsFile));
				canonicalClassNamePatterns = new HashSet<String>();

				String line;
				while((line = reader.readLine()) != null)
				{
					if(!line.isEmpty())
						canonicalClassNamePatterns.add(line);
				}
				reader.close();
			}
			catch(IOException ioe)
			{
				throw new RuntimeException(ioe);
			}
		}
	}

	public String getAccessTransformerClass()
	{
		return null;
	}

	private static void log(Level level, String... messageParts)
	{
		for(String messagePart :messageParts)
			ModelPlayerAPIEnhancerClassVisitor.log(level, messagePart);
	}
}