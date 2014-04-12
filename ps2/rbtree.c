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
 * TODO: Edit this comment to describe why this function runs in time O(n).
 */
bool is_red_black_tree(struct node *root) {
    /* TODO: Implement this! */
    return false;
}

struct node *getLeftChild(struct node *node) {
    uintptr_t ret = (uintptr_t)node->left & 0xFFFFFFFE;
    return (void *) ret;
}

struct node *getRightChild(struct node *node) {
    return node->right;
}

void makeRightChild(struct node *root) {
}

void makeLeft(struct node *root) {
    struct node leftChild;
    root->left = malloc(sizeof(leftChild));
    root->left = &leftChild;
}

bool isNodeRed(struct node *node) {
    return ((uintptr_t)node->left) & 1;
}

void setNodeRed(struct node *node) {
    uintptr_t newLeft = (uintptr_t)node->left | 1;
    node->left = (void *)newLeft;
}

/**
 * Function: to_red_black_tree(int elems[], unsigned length);
 * -------------------------------------------------------------------------
 * Given as input a sorted array of elements, returns a new red/black tree
 * containing the elements of that array.
 *
 * TODO: Edit this comment to describe why this function runs in time O(n).
 */
struct node *to_red_black_tree(int elems[], unsigned length) {
    /* TODO: Implement this! */
    return NULL;
}
