# prerequisite
1. JAVA 8
3. Angular CLI
4. node and NPM
6. logstash

# Run the start.sh file

To run the setup script
1. Navigate to question2 directory
2. Open the logstash.config file and configure the input and output properties (optional)
2. run "bash start.sh"
3. This script copies data from the database to aws elastic search, builds the production angular build and copies the dist directory to java resources, it builds the spring boot application and starts it on 9000 port.

# Run the application in browser
1. Open chrome latest browser
2. Open "http://localhost:9000"

# Link to info file
https://docs.google.com/document/d/1riHC1J2O0fEgG6WgjfOza-s-XmcbRu2FmWBgvzKQrbA/edit?usp=sharing
