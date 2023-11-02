#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAX 100

struct student {
    char name[50];
    int id;
    float marks[5];
    float per; 
};

struct student stack[MAX];
int top = -1; 

void push(struct student s) {
    if (top == MAX - 1) {
        printf("Stack Overflow\n");
        return;
    }
    top++;
    stack[top] = s;
}

struct student pop() {
    if (top == -1) {
        printf("Stack Underflow\n");
        struct student s;
        s.id = -1;
        return s;
    }
    struct student s = stack[top];
    top--;
    return s;
}

void display(struct student s) {
	
    printf("%s\t %d\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\t%.1f\n", s.name, s.id, s.marks[0], s.marks[1], s.marks[2], s.marks[3], s.marks[4],s.per);
    for (int i = 0; i < 5; i++) {
                    scanf("%f", &s.marks[i]);
                }
                s.per = (s.marks[0] + s.marks[1] + s.marks[2] + s.marks[3] + s.marks[4]) / 500;
                s.per=s.per*100;
                printf("%d",s.id);
                push(s);
                printf("--------------------------------------------------------------\n");
}

void search(int id) {
	    printf("Name\tID\tSub1\tSub2\tSub3\tSub4\tSub5\tper\n");

    for (int i = 0; i <= top; i++) {
        if (stack[i].id == id) {
            display(stack[i]);
            return;
        }
    }
    printf("Student with ID %d not found\n", id);
}

int main() {
    int choice, id;
    float per;
    printf("----------------------STUDENT MANAGEMENT SYSTEM----------------------\n");
    struct student s;
    while (1) {
        printf("1. Add Student\n2. Search Student by ID\n3. Exit\n");
                printf("----------------------------------------------------------------------\n");

        printf("Enter your choice: ");
        scanf("%d", &choice);
        printf("----------------------------------------------------------------------\n");
        switch (choice) {
            case 1:
                printf("Enter Name: ");
                scanf("%s", s.name);
                printf("Enter ID: ");
                scanf("%d", &s.id);
                printf("Enter Marks for 5 Subjects: ");
                for (int i = 0; i < 5; i++) {
                    scanf("%f", &s.marks[i]);
                }
                s.per = (s.marks[0] + s.marks[1] + s.marks[2] + s.marks[3] + s.marks[4]) / 500;
                s.per=s.per*100;
                push(s);
                printf("--------------------------------------------------------------\n");
                break;
            
            case 2:
                printf("Enter ID to Search: ");
                scanf("%d", &id);
                search(id);
                break;
            case 3:
                exit(0);
            default:
                printf("Invalid Choice\n");
        }
    }
    return 0;
}

