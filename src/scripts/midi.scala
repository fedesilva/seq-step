
import javax.sound.midi._

def send(rcv: Receiver, chan: Int)(note: Int, vel: Int, len: Int = 1000): Unit = {

  val msgOn = new ShortMessage()
  msgOn.setMessage(ShortMessage.NOTE_ON, chan, note, vel)

  val msgOff = new ShortMessage()
  msgOff.setMessage(ShortMessage.NOTE_OFF, chan, note, 0)

  rcv.send(msgOn, 0)
  Thread.sleep(len)
  rcv.send(msgOff, len)

}

val devices = MidiSystem.getMidiDeviceInfo
val out1 = MidiSystem.getMidiDevice(devices(3))

def introspection(): Unit =
  devices.foreach { dev =>
    println("-" * 50)
    println(dev.getClass)
    println(s"${dev.getVendor}::${dev.getName} : ${dev.getDescription}")
  }

println("opening")
out1.open()

val rcvr = out1.getReceiver

val sender = send(rcvr, 0) _

def play(times: Int = 5): Unit =
  (1 to times).flatMap(
      LazyList
        .continually(
          LazyList(0, 5, 3, 7) ++
          LazyList(0, 3, 3, 5) ++
          LazyList(7, 5, 0, 3)
        )
    ).foreach { off =>
      sender(60 + off, 50, 250)
      Thread.sleep(25)
    }



  




