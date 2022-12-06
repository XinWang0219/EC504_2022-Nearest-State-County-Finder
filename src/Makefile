.SUFFIXES:
.SUFFIXES: .o .cpp
#============================================================
TARGET	=  KnearestCounty

C_SOURCES = kNearestCountyFinder.cpp
JAVA_SOURCES = main.java Point.java KD_tree.java
C_OBJS     = kNearestCountyFinder.o
JAVA_OBJS  = main.class Point.class KD_tree.class
MY_INCLUDES = 

CCX = g++
CXXFLAGS = -g -std=c++1y
#============================================================
all: $(TARGET)

.o:.cpp	$(MY_INCLUDES)
	$(CCX)  -c  $(CXXFLAGS) $<  

$(TARGET) :   $(C_OBJS)
	$(CCX) $(CXXFLAGS)  $^ $(LIBDIRS)  -o $@
	javac $(JAVA_SOURCES)

# Implicit rules: $@ = target name, $< = first prerequisite name, $^ = name of all prerequisites
#============================================================

ALL_SOURCES = makefile $(C_SOURCES) $(JAVA_SOURCES) $(MY_INCLUDES)

clean:
	rm -f $(TARGET) $(C_OBJS) $(JAVA_OBJS) core 
	
	
tar: $(ALL_SOURCES) $(NOTES)
	tar cvf $(TARGET).tar $(ALL_SOURCES)  $(NOTES)

$(TARGET).ps: $(ALL SOURCES)
	enscript -pcode.ps $(ALL_SOURCES)