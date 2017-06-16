

# Structures and types overview

    Sequencer

      * Pattern
    
        * Track
      
          * Channel [SynthChannel|DrumChannel]
         
            * Event [NoteEvent|DrumEvent]

## Sequencer
      
    
## Transport

  Controls playback.

  Given a Clock, i/o ports and a Sequencer it will emit midi messages using the sequencer steps each tick
    of the clock.
    
## Pattern

  The pattern is a container for tracks.
  
## Track and Channels and Steps.

  Tracks aggregate Channels which in turn aggregate sequences of steps.
   
  Tracks declare a midi channel and a step length. 
  
  All tracks in a channel are of the same length, but there is no such restriction for tracks in a pattern.
      
  All steps in a track no matter which channel are emitted at the same time; 
    for a synth it means many notes are emitted providing polyphony where supported, 
    for a drum, each channel is an instrument.
  
  Drum Channels declare a Note besides the steps.
    
  Steps are also different depending on the type of step. Drum steps don't define a note or duration for example.
    
    
###  TODO

  * Make channels length be restricted by the tracks step length.
     - for the time being, check on "play".
     
  
## Clock, Time signature, etc.

   The pattern should be able to define time signatures. 
