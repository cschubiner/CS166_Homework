#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>

/**
 * Function: is_red_black_tree(struct node* root);
 * --------------------------------------------------------------------------
 * Given a pointer to the root of a tree, returns whether that tree is a
 * red/black tree. This function can assume the following:
 *
 * 1. The pointer provided either points to a valid address or to NULL.
 * 2. The tree structure pointed at is indeed a tree; there aren't any
 *    directed or undirected cycles and all internal pointers are valid
 *    (though the left pointers may need to be tweaked a bit to get to
 *    valid data).
 *
 * This code runs in O(n) because every node in the tree is visited once
 * and during each vist, O(1) work is done on each node. A DFS search
 * through a tree with no cycles will at most visit each node only once.
 * In each cycle, I am checking whether the node is NULL and whether it is * a red node. These conditions can be checked in O(1).
 */

static bool is_red_black_tree_helper(struct node* node, int* black_path, int black_path_count);

bool is_red_black_tree(struct node *root)
{
  int black_path = -1; // set as an default value to stand for value needed
  return is_red_black_tree;
}

static bool is_red_black_tree_helper(struct node* node, int* black_path, int black_path_count) {
  if(node == NULL) {
    if(*black_path == -1) {
      *black_path = black_path_count;
      return true;
    }
    else {
      return *black_path == black_path_count;
    }
  }
  if(isNodeRed(node)) {
    if(node->left!= NULL && isNodeRed(node->left)) {
      return false;
    }
    if(node->right!= NULL && isNodeRed(node->right)) {
      return false;
    }
  } else {
    black_path_count ++;
  }

  return is_red_black_tree_helper(node->left, black_path, black_path_count)  &&  is_red_black_tree_helper(node->right, black_path, black_path_count);
}

bool isNodeRed(struct node *node)
{
  return ((uintptr_t)node->left) & 1;
}

void setNodeRed(struct node *node)
{
  node->left |= (void *)1;
}

/**
 * Function: to_red_black_tree(int elems[], unsigned length);
 * -------------------------------------------------------------------------
 * Given as input a sorted array of elements, returns a new red/black tree
 * containing the elements of that array.
 *
 * TODO: Edit this comment to describe why this function runs in time O(n).
 */
struct node *to_red_black_tree(int elems[], unsigned length)
{
  /* TODO: Implement this! */
  return NULL;
}
