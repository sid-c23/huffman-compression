# Huffman Compression
### By Sidhartha Chaganti

This project is a Java implementation of the lossless Huffman Compression technique, which uses prefix codes to reduce the size needed to store each character.

*Note: I'm putting this project on hold because I'm having difficulty writing and reading the compressed data. Namely,
computers can only write byte by byte, not by bit. But, bits are the valuable data in this case for containing the map and data.
However, the computer fails to add zeros which are on the left side of the first 1 in a byte. So, if I want to write
"00110111" to the file, the computer (I believe) will only write "110111". This shows up when I read the file, as those
2 beginning zeros will not be there. I believe this doesn't hinder normal use, but in this case those 0s have meaning,
such as indicating non-leaf nodes in my map. I don't know a way to figure out if I have to add the necessary 0s, so
I believe I cannot continue with the project until I figure out how.*
