

# Structures and types overview

    Sequencer

        * Track (n)
    
          * Pattern (n)
      
              * Step [NoteStep|DrumStep]
              
          * Voice [SynthVoice|DrumVoice]
         
        
        * Snapshot
        
        * Song

## Sequencer
      
    
## Transport

  Controls playback.

  Given a Clock, i/o ports and a Sequencer it will emit midi messages using the sequencer 
    steps each tick of the clock.
    
## Track 

  The track is a container for patterns. Holds info about midi channel and which kind of steps they 
  will contain; synths are different from drums.
  
## Pattern, Voices and Steps.

  A track can have several patterns and each pattern can have different metrics.
    
  A track can have more than one voice, allowing polyphony for synths and multiple instruments for 
  drums. A drum track has a fixed note for each voice, a synth track has potentially a 
  different note per step. 
  
  Steps are note events. For drums only velocity is recorded since as mentioned above 
  the note is fixed per voice.
  
    
## Snapshots and Songs

A Snapshot is a group of links to specific patterns for one or more tracks. It is a snapshot of the 
sequencer at a point in time; what was being played in each track.

Snapshots can be used to create songs.

A Song is a playlist of snapshots. Each snapshot is a part of the song; things like intro, chorus, etc.
A song specifies which patterns in which order and for how long need to be performed to play the song.

## Clock, Time signature, etc.

   The pattern should be able to define time signatures. 

## Live editing

...
    
###  TODO

  * Make Voices length be restricted by the tracks step length.
     - for the time being, check on "play".
     
  
