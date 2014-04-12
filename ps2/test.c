#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <assert.h>

void printTree(struct node * n) {
    if (n == NULL) return;
    printTree(getLeftChild(n));
    printf("%d(%s) ", n->key, isNodeRed(n) ? "red" : "black");
    printTree(n->right);
}


void test1() {

    // struct node rootStruct;

    struct node * root = calloc(1, sizeof(struct node));
    // setNodeRed(root);
    struct node * lStruct = calloc(1, sizeof(struct node));

    struct node * lStruct2 = calloc(1, sizeof(struct node));

    setLeftChild(root, lStruct);
    setRightChild(root, lStruct2);

    setNodeRed(lStruct2);
    // setNodeRed(root->left);


    struct node * third1 = calloc(1, sizeof(struct node));
    struct node * third2 = calloc(1, sizeof(struct node));
    struct node * third3 = calloc(1, sizeof(struct node));
    struct node * third4 = calloc(1, sizeof(struct node));

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
    int arr[] = {2, 4, 6};
    struct node * root = to_red_black_tree(arr, 3);
    assert(root->key == 4);

    printf("* finished creating array\n");
    assert(is_red_black_tree(root));
    printTree(root); printf("\n");
    printf("finished atest1\n\n");

}

void aTest2() {
    int arr[] = {1, 6, 8, 11, 13, 15, 17, 22, 25, 27};
    struct node * root = to_red_black_tree(arr, 10);
    assert(root->key == 13);


    printf("* finished creating array\n");
    printTree(root); printf("\n");

    printf("%d\n", root->key);
    printf("%d\n", getLeftChild(root)->key);

    assert(is_red_black_tree(root));
    printf("finished atest2\n\n");
}

void aTest3() {
    int arr[] = {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17};
    struct node * root = to_red_black_tree(arr, 13);

    printf("* finished creating array\n");
    assert(is_red_black_tree(root));
    printf("finished atest3\n\n");

}



int main() {
    test1();
    test2();
    aTest1();
    aTest2();
    aTest3();

    printf("finished all tests");
}

