#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>

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
 * This code runs in O(n), because every node in the tree is visited once
 * and during each vist, O(1) work is done on each node. A DFS search
 * through a tree with no cycles will at most visit each node only once.
 * In each cycle, I am checking whether the node is NULL and whether it is * a red node. These conditions can be checked in O(1).
 */
static bool is_red_black_tree_helper(struct node * node, int * black_path, int black_path_count);

bool is_red_black_tree(struct node * root) {
    if (isNodeRed(root))
        return false;
    int black_path = -1; // set as an default value to stand for value needed
    return is_red_black_tree_helper(root, &black_path, 0);
}

static bool is_red_black_tree_helper(struct node * node, int * black_path, int black_path_count) {
    if (node == NULL) {
        if (*black_path == -1) {
            *black_path = black_path_count;
            return true;
        } else {

            return *black_path == black_path_count;
        }
    }
    struct node * left = getLeftChild(node);
    struct node * right = getRightChild(node);

    if (isNodeRed(node)) {

        if (left != NULL && isNodeRed(left)) {
            return false;
        }
        if (right != NULL && isNodeRed(right)) {
            return false;
        }
    } else {
        black_path_count ++;
    }

    return is_red_black_tree_helper(left, black_path, black_path_count)  &&  is_red_black_tree_helper(right, black_path, black_path_count);
}


struct node * getLeftChild(struct node * node) {
    return (struct node *)((uintptr_t)node->left & ~1);
}

struct node * getRightChild(struct node * node) {
    return node->right;
}

void setRightChild(struct node * root, struct node * child) {
    root->right = child;
}

void setLeftChild(struct node * root, struct node * child) {
    root->left = (struct node *)((1 & (uintptr_t)root->left) | (uintptr_t)child);
}

bool isNodeRed(struct node * node) {
    if (!node) return false;
    return ((uintptr_t)node->left) & 1;
}

void setNodeRed(struct node * node) {
    if (!node) return;
    if (node->left) {
        uintptr_t newLeft = (uintptr_t)node->left | 1;
        node->left = (void *)newLeft;
    } else {
        node->left = (void *) 1;
    }
}

void setNodeBlack(struct node * node) {
    if (!node) return;
    if (node->left) {
        uintptr_t newLeft = (uintptr_t)node->left & ~1;
        node->left = (void *)newLeft;
    } else {
        node->left = 0;
    }
}

struct node * to_red_black_tree_helper(int elems[], int low, int high, struct node * parent) {
    if (low > high)
        return NULL;

    int mid = low + (high - low) / 2;
    struct node * root = malloc(sizeof(struct node));
    root->key = elems[mid];
    root->parent = parent;

    setNodeRed(root);
    setLeftChild(root, to_red_black_tree_helper(elems, low, mid - 1, root));
    setRightChild(root, to_red_black_tree_helper(elems, mid + 1, high, root));

    return root;
}

void correctColors(struct node * root) {
    if (!root) return;
    correctColors(getLeftChild(root));
    if (isNodeRed(root->parent)) {
        if (root->parent->parent) {
            struct node * rightSib = getRightChild(root->parent->parent);
            struct node * leftSib = getLeftChild(root->parent->parent);
            if (isNodeRed(rightSib) && isNodeRed(leftSib)) {
                setNodeBlack(leftSib);
                setNodeBlack(rightSib);
            }
        }
    }
    correctColors(root->right);
}

/**
 * Function: to_red_black_tree(int elems[], unsigned length);
 * -------------------------------------------------------------------------
 * Given as input a sorted array of elements, returns a new red/black tree
 * containing the elements of that array.
 * This function runs in O(n) time. The function first
 * calls a helper function, which runs in O(n) time, as
 *  each element in the array is visited only once to set
 *   its position in the tree.
 *
 * Then, the function calls correctColors, which also runs
 * in O(n) time, as it simply does an inorder traversal
 * of the tree and runs a O(1) check to see if nodes need
 * recoloring. If recoloring is needed, the recoloring
 * is done in constant time as well.
 */
struct node * to_red_black_tree(int elems[], unsigned length) {
    struct node * root = to_red_black_tree_helper(elems, 0, length - 1, NULL);
    correctColors(root);
    setNodeBlack(root);
    return root;
}
