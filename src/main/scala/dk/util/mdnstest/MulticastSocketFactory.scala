package dk.util.mdnstest

import java.net.{InetAddress, MulticastSocket}


object MulticastSocketFactory {
  
  def create(address: InetAddress, port: Int) = {
    val socket = new MulticastSocket(port)
    socket.joinGroup(address)
    socket
  }

}