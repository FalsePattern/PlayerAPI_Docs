## Render Player API gives third party mods managed access to the player render classes

### Render Player API is a client API which provides access to

* the class "RenderPlayer" aka "byc" and
* the class "ModelPlayer" aka "bly" and
* the class "ModelBiped" aka "bln"

while minimizing conflicts between mods.

## Installation:
For installation instructions:
* make sure you download the right file:
  * Vanilla for mods on a vanilla (non Minecraft Forge) client.
  * Forge or Core for mods on a Minecraft Forge client.
* read the "installation" section of the API's download's readme file

## Incompatibilities
* Render Player API is compatible with Minecraft Forge, just install Render Player API after Minecraft Forge.<br>
  WARNING this does only work before Minecraft 1.6.1.<br>
  For Minecraft 1.6.1 and later use Render Player API forge.<br>
  For Minecraft 1.7.2 and later use Render Player API core.

## FAQs:

Omitted, see original page.

## Developing:

**Step by Step:** Mod Adaption
<details>
<summary>Click to expand</summary>

One scenario of starting to develop with this mod is that you have a fully functional mod that you wrote an own render class and/or model class for for players, and you want your mod to be compatible with other mods that are allready using Render Player API.

In this case the following steps are recommended:

* Move your mod specific code from your render class into RenderPlayer.java then outsource it into a new mod specific class you create a single instance for each instance of the RenderPlayer class so that only the mod class field, some hook methods and some field accessors remain in RenderPlayer.java.
* make sure your mod works correctly.
* Embed Render Player API into your development environment (in case of MCP that means to decompile the Render Player API classes using the MCP decompiling system)
* In case of MCP replace your modded RenderPlayer.java with the RenderPlayer.java from Render Player API client. (and import all other Player API client classes)
  * Let your specific mod class extend the RenderPlayerBase class
  * Make sure your mod class is registered at the RenderPlayerAPI class before the first render instance is created. (a ModLoader mod would be a good choice here)
  * Adapt all your hook listeners in your mod class to overwrite their corresponding RenderPlayerBase methods.
  * Adapt all your field accessor references with references to RenderPlayerAPI field accessors.
* In case your mod did use an own player model class repeat the steps except the embedding step above for your this class and the ModelPlayer.java from the embedded Model Player API now present in your development environment.
* Make sure your mod works correctly.

</details>

**Step by Step:** Mod Creation
<details>
<summary>Click to expand</summary>
z
Another scenario is that you want to develop a new mod with Render Player API.

**In this case the following steps are recommended:**
* Embed Render Player API into your development environment (in case of MCP that means to decompile the Render Player API classes using the MCP decompiling system)
* Start creating a new class that extends your Render Player API's RenderPlayerBase class.
* Make sure your mod's RenderPlayerBase class is registered at the RenderPlayerAPI class before the first player instance is created. (a ModLoader mod would be a good choice here)
* Overwrite the RenderPlayerBase methods you need to make your mod work.
* In case you would like to replace the models inside the RenderPlayer object use the embedded Model Player API instead, just repeat the steps above except the embedding step for the classes ModelPlayerBase and ModelPlayerAPI now present in your development environment.

</details>


**Example:** Mod Structure

<details>
<summary>Click to expand</summary>

In the end your code should look like this:

```java
public class mod_MyModName extends BaseMod {
    public void ModsLoaded() {
        RenderPlayerAPI.register("MyModId", MyModRenderPlayerBase.class);
        ModelPlayerAPI.register("MyModId", MyModModelPlayerBase.class);
    }
}
```

```java
public class MyModRenderPlayerBase extends RenderPlayerBase {
    public MyModRenderPlayerBase(RenderPlayerAPI renderPlayerAPI) {
        super(renderPlayerAPI);
    }

    // one of my hook functions
    public void renderPlayer(EntityPlayer entityplayer, double d, double d1, double d2, float f, float f2) {
        if (myModIsActive) { 
          // my code
        } else {
            super.renderPlayer(entityplayer, d, d1, d2, f, f2);
        }
    }

    // another of my hook functions
    public void loadTexture(String s) {
        if (myModIsActive) {
            // my code
            if (callLoadTextureOfRenderPlayer) {
                renderPlayer.localLoadTexture(s);
            } else if (callLoadTextureOfRenderLiving) {
                renderPlayer.superLoadTexture(s);
            }
        
            // my code
            if (ownTextureToLoad != null) {
                super.loadTexture(ownTextureToLoad);
            } else if (dontLoadTexture) {
                return;
            } else {
                super.setRotationAngles(s);
            }
        } else {
            super.setRotationAngles(s);
        }
    }
}
```

```java
public class MyModModelPlayerBase extends ModelPlayerBase {
    public MyModRenderPlayerBase(ModelPlayerAPI modelplayerapi) {
        super(modelplayerapi);
    }

    // one of my hook functions
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        if (myModIsActive) {
            // my code
        } else {
            super.render(entity, f, f1, f2, f3, f4, f5);
        }
    }
    
    // another of my hook functions
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        if (myModIsActive) {
            // my code
            if (callSetRotationAnglesOfModelPlayer) {
                modelPlayer.localSetRotationAngles(f, f1, f2, f3, f4, f5);
            } else if (callSetRotationAnglesOfModelBeped) {
                modelPlayer.superSetRotationAngles(f, f1, f2, f3, f4, f5);
            }
            
            // my code
            if (hideHead) {
                modelPlayer.bipedHead.isHidden = true;
            } else if (mirrorBody) {
                modelPlayer.bipedBody.mirror = true;
            }
        
        } else {
            super.setRotationAngles(f, f1, f2, f3, f4, f5);
        }
    }
}
```

</details>

## 1.7.10 versions:
[Core 1.4](files/rpapi/MC%201.7.10%20-%20Render%20Player%20API%20core%201.4.zip)

[Enhancer 1.4](files/rpapi/MC%201.7.10%20-%20Render%20Player%20API%20enhancer%201.4.zip)