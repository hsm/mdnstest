package dk.util.mdnstest

object MDNSLookupMatcherFactory {
  
  def create: PartialFunction[Array[Byte], Unit] = {
    case Array(
    0x00, 0x00,
    0x00, 0x00,
    0x00, 0x01,
    0x00, 0x00,
    0x00, 0x00,
    0x00, 0x00,
    length, rest@_*) => {
      val name = rest.slice(0, length)
      println("Got mDNS name lookup for: '" + (name map {_.asInstanceOf[Char]}).mkString + "'")
    }
  }

}