# SodiumPaper (PaperMC 1.12.2)

This repository now includes a **PaperMC 1.12.2 plugin** that brings a server-side optimization profile inspired by Sodium's "reduce overhead" philosophy.

## Important note

Sodium is a **client-side rendering mod**, so it cannot be perfectly recreated as a server plugin. This plugin targets what can be optimized server-side:

- Dynamic monster AI throttling based on player distance.
- Aggressive nearby item stack merging.
- Runtime status + reload command.

## Build

```bash
mvn clean package
```

The output jar will be in `target/sodiumpaper-1.0.0.jar`.

## Commands

- `/sodiumpaper status` - show current optimization stats.
- `/sodiumpaper reload` - reload config (`sodiumpaper.reload`).

## Config

See `src/main/resources/config.yml` for settings.

## Web C++ Prototype

A new `webcraft_cpp/` folder provides a C++ voxel simulation scaffold that can be compiled to WebAssembly with Emscripten as a first step toward a browser-based Minecraft-style implementation.
