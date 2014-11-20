#merging
Building applications that aggregates data from multiple sources is quite common todays. To achieve this, I usually split the work into 3 tasks :
1. Retrieve data from a source into their original format.
2. Fill an object with information retrieved from a source. This object defines the common way to store retrieved data.
3. Merge all objects to obtain only one object with all information at the end.
Often a merging operation is as simple as if I have a value then I keep it otherwise I use the value in the other object. This kind of code is easy to write but at the end you will have an hardcoded merger with a bunch of similar code. I wrote this library while working on aboutmyapp gradle plugin to make the merging easy to write, configurable and extensible.

##Installation
TODO

##Usage
TODO

##License
Source code and binary are distributed under Apache License, Version 2.0.
See LICENSE file for more information.
