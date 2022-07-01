=========================
Render Player API vanilla
=========================

Version 1.0 for Minecraft 1.6.4

by Divisor



Description
===========

Render Player API vanilla is an API which gives client mods access to the client classes "RenderPlayer" aka "bhj" minimizing conflicts between mods.

Additionally it replaces the models inside all instances of RenderPlayer with player specific models that can be accessed via the embedded Player Model API.



Incompatibilities
=================

Render Player API vanilla can *not* be used together with Minecraft Forge, use Render Player API forge instead.



Installation
============

Copy all class files inside this ZIP file to their corresponding locations inside the JAR file of the Minecraft 1.6.4 version you want to install Render Player API vanilla to.

Do NOT use the Minecraft 1.6.4 version "1.6.4". This is the default version which will be redownloaded after every change of yours.

In case no other Minecraft 1.6.4 version is present, create a new one by copying the Minecraft 1.6.4 default version directory "1.6.4" to a new directory (for example: "1.6.4.modded"). Make sure you also change the names of the two copied files and the value of the property "id" inside the copied JSON file accordingly.

And don't forget to make sure there is no "META-INF" folder inside your modded JAR file anymore.

In any case, NEVER forget: ALWAYS back up your stuff!
