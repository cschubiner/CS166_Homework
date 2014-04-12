#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <assert.h>

void test1() {
    struct node rootStruct;
    struct node * root = &rootStruct;
    setNodeRed(root);
    struct node lStruct;
    struct node lStruct2;
    root->left = &lStruct;
    root->right = &lStruct2;
    setNodeRed(root->left);
    setNodeRed(root->right);
    printf("TRUE: is_red_black_tree(root) %s\n", is_red_black_tree(root) ? "TRUE" : "FALSE");
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
    test2();
    aTest1();

    printf("finished all tests");
}

