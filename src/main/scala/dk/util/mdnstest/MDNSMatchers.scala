package dk.util.mdnstest

object MDNSMatchers {

  val lookup: PartialFunction[Array[Int], Unit] = {
    case Array(
    0x00, 0x00,
    0x00, 0x00,
    0x00, 0x01,
    0x00, 0x00,
    0x00, 0x00,
    0x00, 0x00,
    length, rest@_*) => {
      val name = (rest.slice(0, length) map {i: Int => i.toChar}).mkString
      println("Got mDNS name lookup for: '" + name + "'")
    }
  }

  val found: PartialFunction[Array[Int], Unit] = {
    case Array(
    0x00, 0x00,
    0x84, 0x00,
    0x00, 0x00,
    0x00, 0x01,
    0x00, 0x00,
    0x00, _,
    length, rest@_*) => {
      val (nameSlice, tailSlice) = rest.splitAt(length)
      
      val name = (nameSlice map {i: Int => i.toChar}).mkString
      val ip = tailSlice match {
        case Seq(
        0x05, 'l', 'o', 'c', 'a', 'l', 0x00,
        0x00, 0x01,
        0x80, 0x01,
        _, _, _, _,
        0x00, 0x04,
        a, b, c, d,
        _*) => "ip: " + List(a, b, c, d).mkString(".")
        case _ => {
          "unexpected tail format"
        }
      }

      println("Got mDNS name response for: '" + name + "'" + " with " + ip)
    }
  }

  val catchAll: PartialFunction[Array[Int], Unit] = {
    case packet => {
      println("Got unmatched packet: ")
      PacketPrettyPrinter.print(packet)
    }
  }

}