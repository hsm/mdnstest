package dk.util.mdnstest

import java.net.{InetAddress, DatagramPacket, MulticastSocket}


class MDNSSender(socket: MulticastSocket, address: InetAddress, port: Int) {
  
  def send(packet: Array[Byte]) {
    println(String.format("Sending packet to %s:%s", address.getHostAddress, new Integer(port).toString))
    socket.send(new DatagramPacket(packet, 0, packet.size, address, port))
  }

}