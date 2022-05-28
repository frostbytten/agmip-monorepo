#!/bin/bash

# Pathfinding files
curl -sLo src/main/resources/icasa-mvl/metadata.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=557875855"
curl -sLo src/main/resources/icasa-mvl/management_info.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=0"
curl -sLo src/main/resources/icasa-mvl/soils_data.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=250742177"
curl -sLo src/main/resources/icasa-mvl/weather_data.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=2126041133"
curl -sLo src/main/resources/icasa-mvl/measured_data.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=8"
# Code files
curl -sLo src/main/resources/icasa-mvl/metadata_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=5"
curl -sLo src/main/resources/icasa-mvl/crop_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=4"
curl -sLo src/main/resources/icasa-mvl/management_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=2"
curl -sLo src/main/resources/icasa-mvl/pest_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=7"
curl -sLo src/main/resources/icasa-mvl/other_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=6"

