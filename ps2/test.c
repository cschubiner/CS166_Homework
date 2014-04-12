#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <assert.h>

void test1() {

    // struct node rootStruct;
    struct node *root = calloc(1, sizeof(struct node));
    // setNodeRed(root);
    struct node* lStruct = calloc(1, sizeof(struct node));

    struct node* lStruct2 = calloc(1, sizeof(struct node));

    setLeftChild(root, lStruct);
    setRightChild(root, lStruct2);

    setNodeRed(lStruct2);
    // setNodeRed(root->left);


    struct node* third1 = calloc(1, sizeof(struct node));
    struct node* third2 = calloc(1, sizeof(struct node));
    struct node* third3 = calloc(1, sizeof(struct node));
    struct node* third4 = calloc(1, sizeof(struct node));

    setLeftChild(lStruct, third1);
    setRightChild(lStruct, third2);
    setLeftChild(lStruct2, third3);
    setRightChild(lStruct2, third4);

    setNodeRed(third1);
    setNodeRed(third2);

    printf("is_red_black_tree(root) %s\n", is_red_black_tree(root) ? "true" : "false");

    free(root);
    free(lStruct);
    free(lStruct2);

    free(third1);
    free(third2);
    free(third3);
    free(third4);

}

void test2() {
    struct node rootStruct;
    struct node * root = &rootStruct;
    setNodeRed(root);
    struct node lStruct;
    struct node lStruct2;
    struct node lStruct3;
    root->left = &lStruct;
    root->right = &lStruct2;
    setNodeRed(root->left);
    root->left->left = &lStruct3;
    setNodeRed(root->right);
    printf("FALSE: is_red_black_tree(root) %s\n", is_red_black_tree(root) ? "TRUE" : "FALSE");
}

void aTest1() {
    int arr[] = {1, 6, 8, 11, 13, 15, 17, 22, 25, 27};
    struct node * root = to_red_black_tree(arr, 10);
    assert(getLeftChild(root)->key == 8);
    printf("finished atest1\n");

}

int main() {
    test1();
    // test2();
    // aTest1();

    printf("finished all tests");
}

