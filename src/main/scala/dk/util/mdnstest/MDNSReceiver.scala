package dk.util.mdnstest

import java.net.{DatagramPacket, MulticastSocket}

class MDNSReceiver(socket: MulticastSocket,
                   bufferSize: Int,
                   matcher: PartialFunction[Array[Int], Unit]) {

  def receive() {
    val buffer = Array.ofDim[Byte](bufferSize)
    val receivedPacket = new DatagramPacket(buffer, bufferSize)

    socket.receive(receivedPacket)

    val data = receivedPacket.getData.slice(0, receivedPacket.getLength) map {_.toInt & 0xff}

    matcher(data)
  }

}