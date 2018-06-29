/*
 * Rachel Chiang
 * CS 380.01 Computer Networks
 * Project 3: IPv4
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * This program generates a packet with an IPv4 header and sends it to the server
 * (codebank.xyz through port 38003). The length of the data begins as 2B and
 * doubles every time (for a total of 12 packets with the maximum size as 4096).
 * After writing to the server, the server will respond "good" if the packet was
 * constructed according to the project specifications, or it will respond with
 * whatever it believes is the problem.
 */
public class Ipv4Client
{
   public static void main(String[] args)
   {
      try
      {
         // Connect to codebank.xyz on port 38003
         Socket socket = new Socket("codebank.xyz", 38003);
         // Setting up streams
         OutputStream os = socket.getOutputStream();
         InputStream is = socket.getInputStream();
         InputStreamReader isr = new InputStreamReader(is, "UTF-8");
         BufferedReader br = new BufferedReader(isr);
         String msg = "";
         
         System.out.println("Connected to the server.");
         
         boolean canContinue = true;
         int dataLength = 2;
         
         // Send a total of 12 packets, starting at 2B. Double in size each time
         while (canContinue)
         {
            PacketGenerator packetGen = new PacketGenerator(dataLength);
            // Identify each packet being sent by the "data length: n"
            System.out.printf("data length: %d\n", dataLength);
            byte[] packet = packetGen.getPacket();
            
            for (int i = 0; i < packet.length; ++i)
            {
               os.write(packet[i]);
            }
            
            // finally, print out the server's response
            msg = br.readLine();
            System.out.println(msg + "\n");
            if (msg.equals("good"))
            {
               dataLength *= 2;
               if (dataLength > 4096)
               {
                  canContinue = false;
               }
            }
            else
            {
               canContinue = false;
            }
         }
         
         socket.close();
      } catch (UnknownHostException uhe) {
         System.out.print("[M] Could not connect to server.");
         uhe.printStackTrace();
      } catch (IOException ioe) {
         System.out.println("[M] Unexpected IO error occurred.");
         ioe.printStackTrace();
      } finally
      {
         System.out.println("Disconnected from server.");
      }
   }
}
