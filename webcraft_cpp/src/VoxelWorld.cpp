#include "VoxelWorld.h"

#include <algorithm>
#include <cmath>
#include <sstream>

namespace webcraft {

namespace {
char blockChar(BlockType type) {
    switch (type) {
    case BlockType::Air:
        return '.';
    case BlockType::Grass:
        return 'G';
    case BlockType::Dirt:
        return 'D';
    case BlockType::Stone:
        return 'S';
    case BlockType::Water:
        return 'W';
    }
    return '?';
}
} // namespace

VoxelWorld::VoxelWorld(std::size_t width, std::size_t height, std::size_t depth)
    : m_width(width), m_height(height), m_depth(depth), m_blocks(width * height * depth, BlockType::Air) {}

void VoxelWorld::generateTerrain(std::uint32_t seed) {
    const double seedFactor = static_cast<double>((seed % 997) + 1);
    for (std::size_t z = 0; z < m_depth; ++z) {
        for (std::size_t x = 0; x < m_width; ++x) {
            const double noise = std::sin((static_cast<double>(x) + seedFactor) * 0.21) +
                                 std::cos((static_cast<double>(z) + seedFactor) * 0.17);
            const int base = static_cast<int>(m_height / 3);
            const int hill = static_cast<int>(noise * (m_height / 8.0));
            const int terrainHeight = std::clamp(base + hill, 2, static_cast<int>(m_height) - 2);

            for (int y = 0; y <= terrainHeight; ++y) {
                BlockType block = BlockType::Stone;
                if (y == terrainHeight) {
                    block = BlockType::Grass;
                } else if (y >= terrainHeight - 2) {
                    block = BlockType::Dirt;
                }
                setBlock(static_cast<int>(x), y, static_cast<int>(z), block);
            }

            const int waterline = static_cast<int>(m_height / 4);
            for (int y = terrainHeight + 1; y <= waterline; ++y) {
                setBlock(static_cast<int>(x), y, static_cast<int>(z), BlockType::Water);
            }
        }
    }
}

BlockType VoxelWorld::blockAt(int x, int y, int z) const {
    if (!inBounds(x, y, z)) {
        return BlockType::Air;
    }
    return m_blocks[index(static_cast<std::size_t>(x), static_cast<std::size_t>(y), static_cast<std::size_t>(z))];
}

bool VoxelWorld::setBlock(int x, int y, int z, BlockType type) {
    if (!inBounds(x, y, z)) {
        return false;
    }
    m_blocks[index(static_cast<std::size_t>(x), static_cast<std::size_t>(y), static_cast<std::size_t>(z))] = type;
    return true;
}

std::size_t VoxelWorld::countVisibleBlocks() const {
    std::size_t visible = 0;
    constexpr int neighbor[6][3] = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0},
                                    {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};

    for (int z = 0; z < static_cast<int>(m_depth); ++z) {
        for (int y = 0; y < static_cast<int>(m_height); ++y) {
            for (int x = 0; x < static_cast<int>(m_width); ++x) {
                const auto block = blockAt(x, y, z);
                if (block == BlockType::Air) {
                    continue;
                }
                for (const auto &n : neighbor) {
                    if (blockAt(x + n[0], y + n[1], z + n[2]) == BlockType::Air) {
                        ++visible;
                        break;
                    }
                }
            }
        }
    }
    return visible;
}

std::string VoxelWorld::debugSliceY(int y) const {
    std::ostringstream output;
    output << "Slice y=" << y << '\n';
    for (int z = 0; z < static_cast<int>(m_depth); ++z) {
        for (int x = 0; x < static_cast<int>(m_width); ++x) {
            output << blockChar(blockAt(x, y, z));
        }
        output << '\n';
    }
    return output.str();
}

std::size_t VoxelWorld::index(std::size_t x, std::size_t y, std::size_t z) const {
    return y * m_width * m_depth + z * m_width + x;
}

bool VoxelWorld::inBounds(int x, int y, int z) const {
    return x >= 0 && y >= 0 && z >= 0 && x < static_cast<int>(m_width) && y < static_cast<int>(m_height) &&
           z < static_cast<int>(m_depth);
}

} // namespace webcraft
