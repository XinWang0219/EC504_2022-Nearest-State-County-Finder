.SUFFIXES:
.SUFFIXES: .o .cpp
#============================================================
TARGET	=  KnearestCounty

C_SOURCES = kNearestCountyFinder.cpp
C_OBJS     = kNearestCountyFinder.o
MY_INCLUDES = 

CCX = g++
CXXFLAGS = -g -std=c++1y
#============================================================
all: $(TARGET)

.o:.cpp	$(MY_INCLUDES)
	$(CCX)  -c  $(CXXFLAGS) $<  

$(TARGET) :   $(C_OBJS)
	$(CCX) $(CXXFLAGS)  $^ $(LIBDIRS)  -o $@

# Implicit rules: $@ = target name, $< = first prerequisite name, $^ = name of all prerequisites
#============================================================

ALL_SOURCES = makefile $(C_SOURCES) $(MY_INCLUDES)

clean:
	rm -f $(TARGET) $(C_OBJS) core 
	
	
tar: $(ALL_SOURCES) $(NOTES)
	tar cvf $(TARGET).tar $(ALL_SOURCES)  $(NOTES)

$(TARGET).ps: $(ALL SOURCES)
	enscript -pcode.ps $(ALL_SOURCES)