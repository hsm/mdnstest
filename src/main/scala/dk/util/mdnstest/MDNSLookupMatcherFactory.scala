package dk.util.mdnstest

object MDNSLookupMatcherFactory {
  
  def create: PartialFunction[Array[Int], Unit] = {
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

}