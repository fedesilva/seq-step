

# Structures and types overview

    Sequencer

        * Track (n)

          * Pattern (n)
      
              * Step [NoteStep|DrumStep] (n)
              
        
        * Scene
          - points to a sequence of patterns
          - settings like muting of tracks
        
        * Song


    Transport

        - midi clock 
                - Clock events are sent at a rate of 24 pulses per quarter note
            - construct clock with bpm
                - or emit time events and keep track of beats at the Transport level 

        - Emit time events 
        - translate sequences to time events (note on, note off)
            

#  
