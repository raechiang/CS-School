# IPv6
This program generates a packet with an IPv6 header, and then sends it to the server (codebank.xyz through port 38004). The length of the data begins as 2 Bytes and doubles every time, for a total of 12 packets with the maximum size as 4096. After sending to the server, the server will respond with a magic number, which will indicate if the packet was correct `0xCAFEBABE`, or it will send a code that indicates the first problem in the header that it encountered.
## Header Components
IPv6 Header Format [bits]
```
 0                   1                   2                   3
 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1        
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|Version| Traffic Class |           Flow Label                  |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|         Payload Length        |  Next Header  |   Hop Limit   |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                     Source Address [128b]                     |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
|                   Destination Address [128b]                  |
+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
```
The assignment did not involve implementing every component of the header; those that were implemented are as follows: version, payload length, next header, hop limit, source address, and destination address.
