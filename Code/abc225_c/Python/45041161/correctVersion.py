n, m = map(int, input().split())
b = [list(map(int, input().split())) for i in range(n)]
ans = True

for i in range(m-1):
    if b[0][i]+1 != b[0][i+1]:
        ans = False
    if b[0][i]%7 == 0:
        ans = False
    
for i in range(n-1):
    for j in range(m):
        if b[i][j]+7 != b[i+1][j]:
            ans = False
            
print("Yes" if ans else "No")