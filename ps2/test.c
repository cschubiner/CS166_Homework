#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>

void test1() {
    // struct node rootStruct;
    struct node *root = malloc(sizeof(struct node));
    // setNodeRed(root);
    struct node* lStruct = malloc(sizeof(struct node));
    printf("lStruct 1 loc: %x\n", lStruct) ;

    struct node* lStruct2 = malloc(sizeof(struct node));
    printf("lStruct2 loc: %x\n", lStruct2) ;


    lStruct->left = NULL;
    lStruct->right = NULL;
    lStruct2->left = NULL;
    lStruct2->right = NULL;

    root->left = lStruct;
    root->right = lStruct2;

    setNodeRed(root->left);
    // setNodeRed(root->right);

    printf("is_red_black_tree(root) %s\n", is_red_black_tree(root) ? "true" : "false");

    free(root);
    free(lStruct);
    free(lStruct2);
}

int main() {
    test1();
}

