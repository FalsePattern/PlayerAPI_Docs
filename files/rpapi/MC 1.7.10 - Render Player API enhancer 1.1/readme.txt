==========================
Render Player API enhancer
==========================

Version 1.1 for Minecraft 1.7.10

by Divisor



Description
===========

Render Player API enhancer is a Minecraft Forge core mod which tries to modify third party armor models to use Render Player API.

While the modifications done to those armor models at runtime can be beneficial, this can also lead to a completely malfunctioning system.

!! USE WITH CAUTION !!


Implementation
--------------

Render Player API enhancer will violently force ALL classes that:

* extend 'net.minecraft.client.model.ModelBiped' and
* have a canonical name that matches at least one of the candidate patterns

to use Render Player API by

* changing their super class from 'net.minecraft.client.model.ModelBiped' to 'api.player.model.ModelPlayer' and
* removing all locally declared methods that overwrite methods used by Render Player API in case they dont't call their super method at least on one execution path.


Motivation
----------

This can fix various incompatibilities between:

* player animation changing mods and
* player armor model adding mods.

This can also screw everything up, so use with caution.


Candidate Patterns
------------------

Render Player API enhancer can be configured using the file "render_player_api_enhancer_patterns.txt" which is automatically generated during startup when not already present.

It is a simple text file where every line is a 'java.util.regex.Pattern' string.

All classes which transformed canonical class names match at least one of these patterns are considered candidates for being modified by Render Player API enhancer.



Installation
============

Copy the file "%plugin.file%" inside this Render Player API installation package to the "mods" folder of your Minecraft installation. In case this folder does not exist, install Minecraft Forge (http://www.minecraftforge.net) on your client and start the corresponding "Forge" Minecraft version at least once.

In any case, NEVER forget: ALWAYS back up your stuff!



Development Installation
========================

Copy the file "%plugin.file%" inside this Render Player API installation package to the "mods" folder of the runtime environment of your Minecraft Forge development installation. In case this folder does not exist, run your development environment's Minecraft at least once.

In case you want to debug into the Render Player API enhancer source code add the file "%source.file%" to the source lookup path of the run/debug configurations of your Minecraft Forge development installation.