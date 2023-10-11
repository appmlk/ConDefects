N = int(input())
skill = []
for _ in range(N):
    skill.append(list(map(int, input().split())))

need = [False] * N
need[N-1] = True
for i in range(N-1, -1, -1):
    if need[i]:
        for j in skill[i][2:]:
            need[j-1] = True

ans = 0
for i in range(N):
    if need[i]:
        ans += skill[i][0]
print(ans)