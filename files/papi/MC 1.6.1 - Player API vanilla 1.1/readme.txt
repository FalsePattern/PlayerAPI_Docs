==================
Player API vanilla
==================

Version 1.1 for Minecraft 1.6.1

by Divisor



Description
===========

Player API vanilla is an API which gives

* client mods access to the client classes "EntityPlayerSP" aka "bem" and "EntityPlayerMP" aka "js"
* server mods access to the server class and "EntityPlayerMP" aka "js"

while minimizing conflicts between mods.



Incompatibilities
=================

Player API vanilla can *not* be used together with Minecraft Forge, use Player API forge instead.



Installation
============

Client
------

Copy all class files inside this ZIP file to their corresponding locations inside the JAR file of the Minecraft 1.6.1 version you want to install Player API vanilla to.

Do NOT use the Minecraft 1.6.1 version "1.6.1". This is the default version which will be redownloaded after every change of yours.

In case no other Minecraft 1.6.1 version is present, create a new one by copying the Minecraft 1.6.1 default version directory "1.6.1" to a new directory (for example: "1.6.1.modded"). Make sure you also change the names of the two copied files and the value of the property "id" inside the copied JSON file accordingly.

And don't forget to make sure there is no "META-INF" folder inside your modded JAR file anymore.

In any case, NEVER forget: ALWAYS back up your stuff!


Server
------

Copy all class files inside this ZIP file to their corresponding locations in your "minecraft_server.1.6.1.jar".

In any case, NEVER forget: ALWAYS back up your stuff!
