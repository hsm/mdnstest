package dk.util.mdnstest

import java.net.InetAddress

object Main {

  val ip = "224.0.0.251"
  val port = 5353

  def main(args: Array[String]) {
    args match {
      case Array("send", host) => {
        val packet = MDNSLookupQueryFactory.create(host)
        while(true) {
          send(packet)
          Thread.sleep(1000)
        }
      }
      case Array("receive") => {
        while(true) {
          receive()
        }
      }
      case _ => usage()
    }
  }

  def usage() {
    println("Usage: ")
    println("java -jar mdnstest.jar send <host> - send mDNS lookup queries for host every second")
    println("java -jar mdnstest.jar receive - receive mDNS lookup queries")
  }

  def send(packet: Array[Byte]) {
    val address = InetAddress.getByName(ip)
    val socket = MulticastSocketFactory.create(address, port)
    val sender = new MDNSSender(socket, address, port)

    sender.send(packet)
  }

  def receive() {
    val address = InetAddress.getByName(ip)
    val socket = MulticastSocketFactory.create(address, port)
    val receiver = new MDNSReceiver(socket, 100, MDNSLookupMatcherFactory.create)

    receiver.receive()
  }

}