If you need symbolic calculus in Octave:

1 . install 'symbolic' package
    1.1 install python-sympy (for python 3.x)
    1.2 launch octave and execute: pkg install -forge symbolic
2a. set Octave to automatically load it
    `-> add "pkg load symbolic" in ~/.octaverc
2b. or, set it with JavaOctave
    `-> octave.eval("pkg load symbolic")
3.  to update packages, execute "pkg update" within Octave
