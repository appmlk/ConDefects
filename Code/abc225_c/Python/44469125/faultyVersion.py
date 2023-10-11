def a(n,m,B):
    x=B[0][0]
    for i in range(n):
        for j in range(m):
            if B[i][j]!=x+i*7+j:
                return "No"
    return "Yes"
n,m=map(int,input().split())
B=[list(map(int,input().split())) for _ in range(n)]
print(a(n,m,B))