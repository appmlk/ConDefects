N, D = map(int, input().split())

if 2 * D > N - 1:
    print("No")
    exit()

print("Yes")
ans = set()
for i in range(N):
    for j in range(1, D+1):
        if (i, (i+j) % N) in ans or ((i + j) % N, i) in ans:
            continue

        ans.add((i, (i + j) % N))

        if len(ans) == N * D:
            break

    if len(ans) == N * D:
        break

for x in ans:
    print(*map(lambda p: p + 1, x))