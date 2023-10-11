a = input().split()
b = int(a[0])
c = int(a[-1])
d = []
e = []
for j in range(b):
    d.append(input())
for j in range(b):
    e.append(input()) 
def f(x, y):
    for i in range(b):
        for j in range(c):
            if d[(i+x)%b][(j+y)%c] == e[i][j]:
                pass 
            else:
                return 0
    return 1
ans = 0
for i in range(b):
    for j in range(c):
        if f(i, j):
            ans = 1
if ans:
    print('Yes')
else:
    print('No')


    