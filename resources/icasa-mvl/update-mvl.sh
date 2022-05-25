#!/bin/bash

curl -o src/main/resources/icasa-mvl/pathfinder.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=0"
curl -o src/main/resources/icasa-mvl/management_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=2"
curl -o src/main/resources/icasa-mvl/crop_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=4"
curl -o src/main/resources/icasa-mvl/metadata_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=5"
curl -o src/main/resources/icasa-mvl/other_codes.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=6"
curl -o src/main/resources/icasa-mvl/obs_pathfinder.csv "https://docs.google.com/spreadsheets/d/1MYx1ukUsCAM1pcixbVQSu49NU-LfXg-Dtt-ncLBzGAM/pub?output=csv&gid=8"
curl -o src/main/resources/icasa-mvl/metadata_filter.csv "https://docs.google.com/spreadsheets/d/1kngp8bYjFayGtUO4mS7BsxXX8__FG6qlN6UV1a8daog/pub?output=csv&gid=0"
