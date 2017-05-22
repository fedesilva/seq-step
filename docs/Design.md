

# Structures and types overview

    Sequencer

      * Pattern
    
        * Track
      
          * Channel [SynthChannel|DrumChannel]
         
            * Event [NoteEvent|DrumEvent]

## Sequencer
      
   Given a set of Patterns the sequencer will generate a stream of steps. 
   
## Transport

  Controls playback.

  Given a Clock, i/o ports and a Sequencer it will merge the clock pulses with the sequencer steps
    and generate midi messages which will be sent to the output ports. 
      
## Pattern

  The pattern is a container for tracks.
  
## Track and channels.

  Tracks aggregate Channels which in turn aggregate sequences of events.
   
  Tracks declare a midi channel and a step length. 
  
  Drum Channels declare a Note.
  
  All tracks in a channel are of the same length, but there is no such restriction for tracks. 
   
  Channel events for a track are produced at the same time in a step; 
    for a synth it means many notes are emitted providing polyphony where supported, 
    for a drum, each channels is an instrument.
    
    
###  TODO

  * Make channels length be restricted by the tracks step length.
     - for the time being, check on "play".
     
  
## Clock, Time signature, etc.

   The pattern should be able to define time signatures. 
