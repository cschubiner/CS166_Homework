# Makefile modified from http://www.cs.colby.edu/maxwell/courses/tutorials/maketutor
IDIR =.
CC=gcc
CFLAGS=-I$(IDIR) -Wall -std=c99

ODIR=.
LDIR =.

_DEPS = rbtree.h
DEPS = $(patsubst %,$(IDIR)/%,$(_DEPS))

_OBJ = rbtree.o test.c
OBJ = $(patsubst %,$(ODIR)/%,$(_OBJ))

$(ODIR)/%.o: %.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

test: $(OBJ)
	gcc -o $@ $^ $(CFLAGS)

.PHONY: clean

clean:
	rm -f $(ODIR)/*.o *~ core $(INCDIR)/*~
