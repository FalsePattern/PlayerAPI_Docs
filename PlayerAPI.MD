## Player API gives third party mods managed access to the main player class

### Player API is an API which provides access to

* the client class "EntityPlayerSP" aka "bps" or
* the server class "EntityPlayerMP" aka "ly" or
* the bukkit class "EntityPlayer"

while minimizing conflicts between mods.

## Installation:
For installation instructions:
* make sure you download the right file:
    * Vanilla for mods on a vanilla (non Minecraft Forge) client/server.
    * Forge for mods on a Minecraft Forge client/server.
    * Bukkit for bukkit plugins.
    * MCPC+ for mods on a MCPC+ server.
    * Core for mods on a Minecraft Forge client/server or a MCPC+ server.
* read the "installation" section of your download's readme files

## Incompatibilities
* Player API is compatible with Minecraft Forge, just install Player API after Minecraft Forge.<br>
  WARNING this does only work before Minecraft 1.6.1.<br>
  For Minecraft 1.6.1 and later use Render Player API forge.<br>
  For Minecraft 1.7.2 and later use Render Player API core.

## FAQs:

Omitted, see original page.


## Developing:

**Step by Step:** Mod Adaption
<details>
<summary>Click to expand</summary>

One scenario of starting to develop with this mod is that you have a fully functional mod that overwrites the client/server main player class, and you want your mod to be compatible with other mods that are allready using Player API.

In this case the following steps are recommended:

* Outsource your mod specific code from EntityPlayerSP.java/EntityPlayerMP.java into a new mod specific class you create a single instance for each instance of the EntityPlayer class so that only the mod class field, some hook methods and some field accessors remain in EntityPlayerSP.java/EntityPlayerMP.java.
* make sure your mod works correctly.
* Embedd Player API into your development environment (see FAQ#6 and FAQ#9)
* In case of MCP replace your modded EntityPlayerSP.java with the EntityPlayerSP.java from Player API client. (and import all other Player API client classes)
  * Let your specific mod class extend the PlayerBase class
  * Make sure your mod class is registered at the PlayerAPI class before the first player instance is created. (a ModLoader mod would be a good choice here)
  * Adapt all your hook listeners in your mod class to overwrite their corresponding PlayerBase methods.
  * Adapt all your field accessor references with references to PlayerAPI field accessors.
* Check whether required hooks are missing and send me a private message containing the corresponding EntityPlayerSP/EntityPlayerMP method names (and wait until I updated Player API) if necessary. (NOTE: THIS NO LONGER WORKS, MAINTAINER IS GONE, CODE IS OBFUSCATED)
* Make sure your mod works correctly.

</details>

**Step by Step:** Mod Creation
<details>
<summary>Click to expand</summary>

Another scenario is that you want to develop a new mod with Player API.

In this case the following steps are recommended:
* Embedd Player API into your development environment (FAQ to decompile the Player API classes using the MCP decompiling system)
* Start creating a new class that extends your Player API's ClientPlayerBase class.
* Make sure your mod's ClientPlayerBase class is registered at the PlayerAPI class before the first player instance is created. (a ModLoader mod would be a good choice here)
* Overwrite the ClientPlayerBase methods you need to make your mod work.

</details>


**Example:** Mod Structure

<details>
<summary>Click to expand</summary>

In the end your code should look like this:

```java
public class mod_MyModName extends BaseMod {
  public void modsLoaded() {
    // for the "client" players
    ClientPlayerAPI.register("MyModId", MyModClientPlayerBase.class);

    // for the "server" players
    ServerPlayerAPI.register("MyModId", MyModServerPlayerBase.class);
  }
}
```

or

```java
@Mod(modid="MyModId")
public class MyModName {
  public MyModName() {
    // for the "client" players
    ClientPlayerAPI.register("MyModId", MyModClientPlayerBase.class);

    // for the "server" players
    ServerPlayerAPI.register("MyModId", MyModServerPlayerBase.class);
  }
}
```

and

```java
public class MyModClientPlayerBase extends ClientPlayerBase {
  public MyModClientPlayerBase(ClientPlayerAPI playerapi) {
    super(playerapi);
  }

  // one of my client player hook functions
  public void jump() {
    if (myModIsActive) {
      // my code
      if (callJumpOfEntityPlayerSP) {
        player.localJump();
      } else if (callJumpOfEntityPlayer) {
        player.superJump();
      }

      // my code
      if (doGigaJump) {
        player.motionY = gigaJumpSpeed;
      } else if (doMiniJump) {
        player.motionY = miniJumpSpeed;
      }
    } else {
      super.jump();
    }
  }
}
```

and/or

```java
public class MyModServerPlayerBase extends ServerPlayerBase {
  public MyModServerPlayerBase(ServerPlayerAPI playerapi) {
    super(playerapi);
  }

  // one of my server player hook functions
  public boolean canBreatheUnderwater() {
    if (myModIsActive) {
      // my code
      return myResult;
    } else {
      return super.canBreatheUnderwater();
    }
  }
}
```

</details>

# For the files, see `files/papi`