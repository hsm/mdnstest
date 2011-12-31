package dk.util.mdnstest

import java.net.InetAddress

object Main {

  val ip = "224.0.0.251"
  val port = 5353

  def main(args: Array[String]) {
    args match {
      case Array("send", host) => send(host)
      case Array("receive") => receive()
      case _ => usage()
    }
  }

  def usage() {
    println("Usage: ")
    println("java -jar mdnstest.jar send <host> - send mDNS lookup queries for host every second")
    println("java -jar mdnstest.jar receive - receive mDNS lookup queries")
  }

  def send(host: String) {
    val packet = MDNSLookupQueryFactory.create(host)

    val address = InetAddress.getByName(ip)
    val socket = MulticastSocketFactory.create(address, port)
    val sender = new MDNSSender(socket, address, port)

    while (true) {
      sender.send(packet)
      Thread.sleep(1000)
    }
  }

  def receive() {
    val address = InetAddress.getByName(ip)
    val socket = MulticastSocketFactory.create(address, port)
    val matcher = MDNSMatchers.lookup orElse MDNSMatchers.found orElse MDNSMatchers.catchAll

    val receiver = new MDNSReceiver(socket, 0xffff, matcher)


    while (true) {
      receiver.receive()
    }
  }

}