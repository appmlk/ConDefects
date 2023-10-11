import copy

n = int(input())
a = [input().split() for _ in range(n)]
b = [input().split() for _ in range(n)]

def is_same(m):
    for i in range(n):
        for j in range(n):
            if m[i][j] != '1':
                continue
            if m[i][j] != b[i][j]:
                return False
    return True
    

def rotate(m):
    rotated = [[-1 for _ in range(n)] for _ in range(n)]
    for i in range(n):
         for j in range(n):
            rotated[i][j] = m[n-1-j][i]
    return rotated
    
prev = a
for i in range(3):
    rotated = rotate(prev)
    if is_same(rotated):
        print("Yes")
        exit(0)
    prev = rotated
print("No")