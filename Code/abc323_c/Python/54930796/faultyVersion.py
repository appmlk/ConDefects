n, m = map(int, input().split())
a = list(map(int, input().split()))
s = [input() for _ in range(n)]

score = [i+1 for i in range(n)]
for i in range(n):
    for j in range(m):
        if s[i][j] == "o":
            score[i] += a[j]
top = max(score)

for i in range(n):
    need = top - score[i]
    rest = []
    for j in range(m):
        if s[i][j] == "x":
            rest.append(a[j])
    rest.sort(reverse=True)
    ans = 0
    for j in range(len(rest)+1):
        if need <= 0:
            ans = j
            break
        need -= a[j]
    print(ans)