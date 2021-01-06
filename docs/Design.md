

# Structures and types overview

#### To Do

* Review model
* Commit and merge
* Simple Transport


## Design

### Sequencer

  

* Track (n)

  * track attributes:
    * midi channel
    * default velocity

      * Pattern (n)
  
          * Step [NoteStep|DrumStep] (n)
          
  * Tracks are 
    
* Scene
  - points to a sequence of patterns
  - settings like muting of tracks

* Song
  * Collection of scenes
  * Can be repeated

### Transport

- midi clock 
    - Clock events are sent at a rate of 24 pulses per quarter note
    - construct transport with bpm
        - emit low level time events and emit proper midi clock pulses depending on bpm settings  

- Emit clock events, note events
    - interpret the sequences (particularly, when the notes end, since note on is evident)
    

            

#  
