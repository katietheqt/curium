# Curium
A project to strip the rendering code out of a Minecraft client.

### Why?
- Fabric loader updates near-instantly to snapshots and new releases
- Reimplementations of the game are less accurate, and can therefore be detected server-side
- Allows somewhat seamless porting of existing mods (renderer code may need to be edited)

### Goals
- Minimal CPU and RAM usage
- No dependency on LWJGL or GPU hardware
- Ergonomic API for replaced features (e.g. keyboard or mouse spoofing)

### API
Everything outside the `me.katie.curium.impl` package is part of the public API.