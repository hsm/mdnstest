package dk.util.mdnstest

object MDNSLookupQueryFactory {

  def create(name: String) = {
    println("Creating packet for name lookup on '" + name + "'")
    
    val before = Array[Byte] (
      0x00, 0x00,
      0x00, 0x00,
      0x00, 0x01,
      0x00, 0x00,
      0x00, 0x00,
      0x00, 0x00
    )
    val after = Array[Byte] (
      0x05, 'l', 'o', 'c', 'a', 'l', 0x00,
      0x00, 0x01,
      0x00, 0x01
    )

    (before :+ name.size.asInstanceOf[Byte]) ++ name.getBytes ++ after
  }

}