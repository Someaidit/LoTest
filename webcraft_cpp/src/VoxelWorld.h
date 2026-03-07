#pragma once

#include <cstddef>
#include <cstdint>
#include <string>
#include <vector>

namespace webcraft {

struct Vec3i {
    int x;
    int y;
    int z;
};

enum class BlockType : std::uint8_t {
    Air = 0,
    Grass = 1,
    Dirt = 2,
    Stone = 3,
    Water = 4
};

class VoxelWorld {
  public:
    VoxelWorld(std::size_t width, std::size_t height, std::size_t depth);

    void generateTerrain(std::uint32_t seed);
    [[nodiscard]] BlockType blockAt(int x, int y, int z) const;
    bool setBlock(int x, int y, int z, BlockType type);
    [[nodiscard]] std::size_t countVisibleBlocks() const;
    [[nodiscard]] std::string debugSliceY(int y) const;

  private:
    std::size_t index(std::size_t x, std::size_t y, std::size_t z) const;
    bool inBounds(int x, int y, int z) const;

    std::size_t m_width;
    std::size_t m_height;
    std::size_t m_depth;
    std::vector<BlockType> m_blocks;
};

} // namespace webcraft
