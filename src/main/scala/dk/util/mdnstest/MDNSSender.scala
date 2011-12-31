package dk.util.mdnstest

import java.net.{InetAddress, DatagramPacket, MulticastSocket}


class MDNSSender(socket: MulticastSocket, address: InetAddress, port: Int) {
  
  def send(packet: Array[Int]) {
    println(String.format("Sending packet to %s:%s", address.getHostAddress, new Integer(port).toString))
    val bytePacket = packet map {_.toByte}
    socket.send(new DatagramPacket(bytePacket, 0, bytePacket.size, address, port))
  }

}