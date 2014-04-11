#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>

int main()
{
  struct node root;
  printf("red %d", isNodeRed(&root));
}
