.SUFFIXES:
.SUFFIXES: .o .cpp
#============================================================
TARGET	=  shortest

C_SOURCES =  kNearestCountyFinder.cpp
C_OBJS     =  finder.o
MY_INCLUDES =

CCX = g++
CXXFLAGS = -g -std=c++14
#============================================================
all: $(TARGET)

.o:.cpp	$(MY_INCLUDES)
	$(CCX)  -c  $(CXXFLAGS) $<  

$(TARGET) :   $(C_OBJS)
	$(CCX) $(CXXFLAGS)  $^ $(LIBDIRS)  -o $@


#============================================================
all: $(TARGET)

.o:.cpp	$(MY_INCLUDES)
	$(CCX)  -c  $(CXXFLAGS) $<  

$(TARGET) :   $(C_OBJS)
	$(CCX) $(CXXFLAGS)  $^ $(LIBDIRS)  -o $@

#============================================================
clean:
	rm -f $(TARGET) $(C_OBJS) core 