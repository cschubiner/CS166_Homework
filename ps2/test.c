#include <stdio.h>
#include "rbtree.h"
#include <stddef.h>
#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <assert.h>


void test1() {

    // struct node rootStruct;
    struct node * root = malloc(sizeof(struct node));
    // setNodeRed(root);
    struct node * lStruct = malloc(sizeof(struct node));
    printf("lStruct 1 loc: %x\n", lStruct) ;

    struct node * lStruct2 = malloc(sizeof(struct node));
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
    printf("finished atest1\n");

}

void aTest2() {
    int arr[] = {1, 6, 8, 11, 13, 15, 17, 22, 25, 27};
    struct node * root = to_red_black_tree(arr, 10);
    assert(root->key == 13);


    printf("* finished creating array\n");
    assert(is_red_black_tree(root));
    printf("finished atest2\n");

}

void aTest3() {
    int arr[] = {3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17};
    struct node * root = to_red_black_tree(arr, 13);
    assert(root->key == 12);

    printf("* finished creating array\n");
    assert(is_red_black_tree(root));
    printf("finished atest3\n");

}


int main() {
    // test1();
    // test2();
    aTest1();
    aTest2();
    aTest3();

    printf("finished all tests");
}

