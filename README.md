# Scrambler
Scrambler is a java library made for scrambling strings.
# How to Use
To scramble some text first you need create an instance of Scrambler.

Scrambler scrambler = new Scrambler();
String encoded = scrambler.encode(<ScrambleSet Id>, <String to Encode>);

This will encode you string, to decode it is the same operation.

Scrambler scrambler = new Scrambler();
String decoded = scrambler.decode(<ScrambleSet Id>, <Encoded String>);

To generate a ScrambleSet you use the Scrambler class to.

Scrambler scrambler = new Scrambler();
UUID id = scrambler.generateScrambleSet(<int keysetLength>);

#Scramble Settings
To change settings you need to access the ScrambleSettings class.
ScrambleSettings.showLogs(<boolean value>);
ScrambleSettings.showTestLogs(<boolean value>);
