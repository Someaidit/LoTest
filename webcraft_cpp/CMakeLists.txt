cmake_minimum_required(VERSION 3.16)
project(webcraft_cpp LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

add_executable(webcraft_demo
    src/main.cpp
    src/VoxelWorld.cpp
)

target_include_directories(webcraft_demo PRIVATE src)

if(EMSCRIPTEN)
    set_target_properties(webcraft_demo PROPERTIES SUFFIX ".html")
    target_link_options(webcraft_demo PRIVATE
        "-sALLOW_MEMORY_GROWTH=1"
        "-sWASM=1"
        "-sASSERTIONS=1"
    )
endif()
