# WebCraft C++ (Minecraft 1.12-inspired web prototype)

This is a **from-scratch C++ voxel prototype** designed to run natively or be compiled for the web with Emscripten.

## What it currently includes

- Voxel world container (`32x32x32` by default).
- Deterministic terrain generation with layered blocks (stone/dirt/grass/water).
- Basic block edits during a tick simulation.
- A visible-block counter (cheap stand-in for mesh culling work).
- Debug slice output for quick verification.

> This is a technical foundation, not a full legal or feature-complete clone of Minecraft 1.12.

## Native build

```bash
cmake -S webcraft_cpp -B webcraft_cpp/build
cmake --build webcraft_cpp/build
./webcraft_cpp/build/webcraft_demo 120 1122
```

Arguments:

1. `ticks` (optional, default `180`)
2. `seed` (optional, default `1122`)

## Web build (Emscripten)

```bash
emcmake cmake -S webcraft_cpp -B webcraft_cpp/build-web
cmake --build webcraft_cpp/build-web
```

The build emits an HTML launcher and WASM module (`webcraft_demo.html/.wasm`) that can be hosted statically.

## Next milestones

- Add chunk streaming and meshing.
- Add real camera, physics, and input abstraction.
- Integrate WebGL renderer (or bgfx/wgpu bridge).
- Add networking protocol compatibility layer for multiplayer.
