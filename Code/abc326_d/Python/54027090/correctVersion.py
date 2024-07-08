N = int(input())
R,C = input(),input()


def dfs(y, x, a):
    if(y == N):
        if(all("".join(sorted([ai[j] for ai in a])).strip('.').strip('.') == "ABC" and "".join([ai[j] for ai in a]).strip('.').strip('.')[0] == C[j] for j in range(N))):
            print("Yes")
            print("\n".join("".join(row) for row in a))
            exit(0)
    elif(x == N):
        if("".join(sorted(a[y])).strip('.').strip('.') == "ABC" and "".join(a[y]).strip('.').strip('.')[0] == R[y]):
            dfs(y + 1, 0, a)
    else:
        dfs(y, x + 1, a)
        row = list(filter(lambda c:c != '.',a[y]))
        col = list(filter(lambda c:c != '.',[ai[x] for ai in a]))
        for ch in "ABC":
            if row == [] and ch != R[y] or ch in row:
                continue
            if col == [] and ch != C[x] or ch in col:
                continue
            a[y][x] = ch
            dfs(y, x + 1, a)
            a[y][x] = "."


dfs(0, 0, [["."] * N for _ in range(N)])
print("No")