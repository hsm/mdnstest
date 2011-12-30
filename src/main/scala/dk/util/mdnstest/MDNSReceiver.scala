package dk.util.mdnstest

import java.net.{DatagramPacket, MulticastSocket}


class MDNSReceiver(socket: MulticastSocket,
                   bufferSize: Int,
                   matcher: PartialFunction[Array[Byte], Unit]) {

  def receive() {
    val buffer = Array.ofDim[Byte](bufferSize)
    val receivedPacket = new DatagramPacket(buffer, bufferSize)

    socket.receive(receivedPacket)

    val data = receivedPacket.getData

    if (matcher.isDefinedAt(data)) matcher(data) else println("Got unmatched packet")
  }

}