#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>

void test1() {
    struct node rootStruct;
    struct node *root = &rootStruct;
    setNodeRed(root);
    struct node lStruct;
    struct node lStruct2;
    root->left = &lStruct;
    root->right = &lStruct2;
    setNodeRed(root->left);
    setNodeRed(root->right);
    printf("is_red_black_tree(root) %s", is_red_black_tree(root) ? "true" : "false");
}

int main() {
    test1();
}

