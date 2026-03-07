#include "VoxelWorld.h"

#include <algorithm>
#include <cstdint>
#include <cstdlib>
#include <iostream>

struct SimulationOptions {
    int ticks = 180;
    std::uint32_t seed = 1122;
};

SimulationOptions parseOptions(int argc, char **argv) {
    SimulationOptions options;
    if (argc > 1) {
        options.ticks = std::max(1, std::atoi(argv[1]));
    }
    if (argc > 2) {
        options.seed = static_cast<std::uint32_t>(std::strtoul(argv[2], nullptr, 10));
    }
    return options;
}

int main(int argc, char **argv) {
    const auto options = parseOptions(argc, argv);

    webcraft::VoxelWorld world(32, 32, 32);
    world.generateTerrain(options.seed);

    int playerX = 16;
    int playerY = 24;
    int playerZ = 16;

    for (int tick = 0; tick < options.ticks; ++tick) {
        const int drift = (tick % 2 == 0) ? 1 : -1;
        playerX = std::clamp(playerX + drift, 2, 29);

        if (tick % 30 == 0) {
            world.setBlock(playerX, playerY - 1, playerZ, webcraft::BlockType::Stone);
        }
        if (tick % 45 == 0) {
            world.setBlock(playerX + 1, playerY - 1, playerZ, webcraft::BlockType::Air);
        }
    }

    std::cout << "WebCraft C++ simulation complete\n";
    std::cout << "Ticks: " << options.ticks << "\n";
    std::cout << "Seed: " << options.seed << "\n";
    std::cout << "Visible blocks: " << world.countVisibleBlocks() << "\n\n";
    std::cout << world.debugSliceY(20);

    return 0;
}
