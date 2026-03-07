# SodiumPaper (PaperMC 1.12.2)

SodiumPaper is a Paper 1.12.2 optimization plugin focused on **lag-free, low-risk server tuning**.

## What's included

- 100 built-in feature toggles (auto-generated in `config.yml` on first run).
- Runtime optimization loop for:
  - Dynamic monster AI throttling.
  - Nearby item stack merging.
  - Arrow / XP orb cleanup.
  - Spawn cap / spawn interval tuning.
  - Gamerule tuning (`randomTickSpeed`, `maxEntityCramming`).
- Commands:
  - `/sodiumpaper status`
  - `/sodiumpaper features [page] [all|enabled|disabled]`
  - `/sodiumpaper reload`

## Build

```bash
mvn clean package
```

The output JAR is placed in `target/sodiumpaper-1.1.0.jar`.
