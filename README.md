# musa_2
The second version of MUSA




#Some notes about using eclipse

1. clone the repo in a local folder
2. launch eclipse and import 
file->import
jacamo->jacamo project
select the source file (where the repo has been cloned)
click finish
(eclipse marks the project is already set as a git project)
switch to the java perspective

[if necessary]
3. convert to jacamo project
by changing the nature of the project
edit the .project in the root directory
and add:
			<buildCommand>
            	<name>jacamoide.jacamoBuilder</name>
                <arguments>
                </arguments>
         	</buildCommand>
(add to <buildSpec>)
and
			<nature>jacamoide.jacamoNature</nature>
(add to <natures>)
			
Now eclipse marks the project as jacamo
switch to the jacamo perspective


