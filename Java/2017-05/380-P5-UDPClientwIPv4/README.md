# UDP with IPv4
This program creates packets with an IPv4 header with UDP as its data and random bytes as the UDP's data. The IPv4 header is constructed approximately identically to the one in <a href="https://github.com/raechiang/CS-School/tree/master/Java/2017-04/380-P3-IPv4">project 3</a>; the protocol is set to UDP instead of TCP. The following outlines the overall procedure:
<ol>
  <li> "Handshaking"
    <ol>
      <li>The client will send a single IPv4 packet (without UDP), with 4 Bytes of hard-coded data `0xDEADBEEF`.</li>
      <li>The server will respond with a 4 Byte code. If the response was `0xCAFEBABE`, the server will send 2 Bytes of raw data representing a 16-bit unsigned integer corresponding to a port number, which will be the destination port of the UDP.</li>
    </ol>
  </li>
  <li>The client will send UDP packets inside IPv4 packets, beginning with the UDP data containing 2 Bytes of random data, doubling each time, for a total of 12 packets.</li>
  <li>After each UDP packet is sent, the server will respond with a 4 Byte magic number, which will be printed out along with the milliseconds of time elapsed since the packet was sent for each packet transmitted.</li>
  <li>Once all 12 packets have been sent, the mean for the round-trip time will be printed.</li>
</ol>
