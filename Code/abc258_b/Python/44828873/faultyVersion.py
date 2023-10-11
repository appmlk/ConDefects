n = int(input())
A = [input() for _ in range(n)]

D = [(1,0),(1,-1),(0,-1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1)]

ans = []
for r in range(n):
    for c in range(n):
        for dr,dc in D:
            tmp = []
            for i in range(n):
                tmp.append(A[(r+dr*i)%n][(c+dc*i)%n])
        ans.append("".join(tmp))

ans.sort()
print(ans[-1])