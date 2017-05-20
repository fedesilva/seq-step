

# Structures and types overview

    Sequencer
  
      * Track
    
        * Pattern [SynthPattern|DrumPattern]
       
          * Event [NoteEvent|DrumEvent]
      
      
## Sequencer

  The sequencer is the top level structure. It controls the clock and groups the patterns.
  
## Track and patterns.

  Tracks aggregate Patterns which in turn aggregate sequences of events.
   
  Tracks declare a midi channel and a step length. 
  
  Drum Patterns declare a Note.
  
  All tracks in a pattern are of the same length, but there is no such restriction for tracks. 
   
  Pattern events for a track are produced at the same time in a step; 
    for a synth it means many notes are emitted providing polyphony where supported, 
    for a drum, each patterns is an instrument.
    
    
###  TODO

  * Make patterns length be restricted by the tracks step length.
     - for the time being, check on "play".
     
  
## Clock, Time signature, etc.

   The sequencer should be able to define time signatures. 
