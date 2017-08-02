
import javax.sound.midi._


def send(rcv: Receiver, chan: Int)( note: Int, vel: Int, len: Int = 1000) = {
  
  val msgOn = new ShortMessage()
  msgOn.setMessage(ShortMessage.NOTE_ON, chan, note, vel)
  
  val msgOff = new ShortMessage()
  msgOff.setMessage(ShortMessage.NOTE_OFF, chan, note, 0)

  rcv.send(msgOn, 0)
  Thread.sleep(len)
  rcv.send(msgOff, len)
  
}


val devices = MidiSystem.getMidiDeviceInfo

val bus1 = MidiSystem.getMidiDevice(devices(2))

bus1.open()

val rcvr = bus1.getReceiver

val sender = send(rcvr, 0) _

def play(times: Int = 5) = {
  val ns = (0 to times).zip(Stream.continually(List(0,5,3,7).toStream).flatten)
  ns.foreach{
    case (i, off) =>
      sender(60+off, 50, 250)
      //Thread.sleep(1000)
  }
}







