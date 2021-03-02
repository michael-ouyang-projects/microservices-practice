jps | FindStr SNAPSHOT.jar > tmp.txt
FOR /F %%A IN (tmp.txt) DO taskkill /F /PID %%A
del tmp.txt