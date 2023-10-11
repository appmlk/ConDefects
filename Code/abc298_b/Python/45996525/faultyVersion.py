# B

n = int(input())
a = []
for _ in range(n):
    cur_a = list(map(int, input().split()))
    a.append(cur_a)
b = []
for _ in range(n):
    cur_b = list(map(int, input().split()))
    b.append(cur_b)

'''
n = 3
a = [[0, 1, 1], [1, 0, 0], [0, 1, 0]]
b = [[1, 1, 0], [0, 0, 1], [1, 1, 1]]
'''
'''
n = 5
a = [[0, 0, 1, 1, 0], [1, 0, 0, 1, 0], [0, 0, 1, 0, 1], [0, 1, 0, 1, 0], [0, 1, 0, 0, 1]]
b = [[1, 1, 0, 0, 1], [0, 1, 1, 1, 0], [0, 0, 1, 1, 1], [1, 0, 1, 0, 1], [1, 1, 0, 1, 0]]
'''
#print(a)
#print(b)
import numpy as np
for i in range(1,4):
    #print("=====================")
    #print(i)
    a90 = np.rot90(a, k = -i)
    #print(a90)
    ans = True
    for j in range(n):
        for k in range(n):
            #ans = True
            #print("-----------------")
            #print("j, k", j, k)
            #print("a90:::::::::", a90[j][k])
            if a90[j][k] == 1:
                #print("b[j][j]:::::", b[j][k])
                if b[j][k] != 1:
                    ans = False
                    #print("NG::::::::::::::::::::::")
    if ans:
        break   
if ans:
    print("Yes")
else:
    print("No")